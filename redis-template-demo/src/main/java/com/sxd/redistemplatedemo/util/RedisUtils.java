package com.sxd.redistemplatedemo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author 李健新
 * @Date 2022/4/2
 * @Description
 *
 *      Redis工具类
 *
 */
@Component
public class RedisUtils {

    private final static Logger log = LoggerFactory.getLogger(RedisUtils.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    // #####################################################【key】#####################################################

    /**
     * 获取值
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 批量获取值
     *
     * @param keys
     * @return
     */
    public List<Object> multiGet(Collection<String> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return null;
        }
        return redisTemplate.opsForValue().multiGet(keys);
    }


    /**
     * 删除对象，支持批量删除
     *
     * @param key
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(Arrays.asList(key));
            }
        }
    }

    /**
     * 判断指定对象是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 获取对象的过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回-1, 代表为永久有效
     */
    public long getKeyExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 指定对象失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expireKey(String key, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, timeUnit);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 通过increment(K key, long increment)方法以增量方式存储long值（正值则自增，负值则自减）
     *
     * @param key
     * @param increment
     * @return
     */
    public void increment(String key, long increment) {
        redisTemplate.opsForValue().increment(key, increment);
    }

    /**
     * 通过increment(K key, double increment)方法以增量方式存储double值（正值则自增，负值则自减）
     *
     * @param key
     * @param increment
     * @return
     */
    public void increment(String key, double increment) {
        redisTemplate.opsForValue().increment(key, increment);
    }

    /**
     * 修改key的名称
     *
     * @param oldKey
     * @param newKey
     */
    public void renameKey(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * 如果旧值key存在时，修改key的名称
     *
     * @param oldKey
     * @param newKey
     * @return
     */
    public Boolean renameOldKeyIfAbsent(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 将当前传入的key值序列化为byte[]类型
     *
     * @param key
     * @return
     */
    public byte[] dump(String key){
        return redisTemplate.dump(key);
    }

    /**
     * 模糊搜索key
     *
     * @param pattern
     * @return
     */
    public Set<String> getPatternKey(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 随机获取一个key
     * @return
     */
    public String randomKey(){
        return redisTemplate.randomKey();
    }

    /**
     * 获取传入key所存储的值的类型
     * @param key
     * @return
     */
    public DataType getKeyType(String key) {
        return redisTemplate.type(key);
    }


    // #####################################################【string】#####################################################
    // string 类型是二进制安全的，即可以包含任何数据。
    // string 类型的值最大能存储 512MB。

    /**
     * 新增string
     *
     * @param key       key
     * @param value     value
     * @return
     */
    public boolean stringSet(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 新增具有过期时间string
     *
     * @param key         key
     * @param value       value
     * @param time        时间
     * @param timeUnit    时间单位
     * @return
     */
    public boolean stringSet(String key, Object value, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, timeUnit);
            } else {
                stringSet(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 设置key的新值并返回旧值
     *      对象不存在时新增对象并返回null
     *
     * @param key       key
     * @param value     value
     * @return          旧值
     */
    public Object stringGetAndSet(String key, Object value) {
        try {
            Object andSet = redisTemplate.opsForValue().getAndSet(key, value);
            return andSet;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 不存在指定key时新增且返回true
     * 存在指定key则返回false
     *
     * @param key       key
     * @param value     value
     * @return
     */
    public boolean stringSetIfAbsent(String key, String value) {
        try {
            return redisTemplate.opsForValue().setIfAbsent(key, value);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 批量设置string
     *
     * @param values  key - value 键值对
     * @return
     */
    public boolean stringMultiSet(Map<String, Object> values) {
        try {
            redisTemplate.opsForValue().multiSet(values);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 全部key都不存在时新增
     *
     * @param values
     * @return
     */
    public boolean stringMultiSetIfAbsent(Map<String, Object> values) {
        try {
            redisTemplate.opsForValue().multiSetIfAbsent(values);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }


    /**
     * 在原有value后追加字符串
     *
     * @param key
     * @param value
     * @return
     */
    public boolean stringAppend(String key, String value) {
        try {
            redisTemplate.opsForValue().append(key, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 返回key中字符串的子字符
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public String stringGetCharacterRange(String key, long start, long end) {
        return redisTemplate.opsForValue().get(key, start, end);
    }

    /**
     * 部份覆盖指定字符串
     *
     * @param key
     * @param value
     * @param offset
     */
    public void StringSetRange(String key, String value, long offset){
        redisTemplate.opsForValue().set(key, value, offset);
    }

    // #####################################################【Bit】#####################################################

    /**
     * 设置Bit
     * @param key
     * @param offset
     * @param value
     */
    public void bitSet(String key, long offset, boolean value){
        redisTemplate.opsForValue().setBit(key, offset, value);
    }

    /**
     * 获取Bit
     * @param key
     * @param offset
     */
    public Boolean getBit(String key, long offset){
        return redisTemplate.opsForValue().getBit(key, offset);
    }

    // #####################################################【Hash】#####################################################
    // hash 是一个 string 类型的 field（字段） 和 value（值） 的映射表。
    // 每个Hash可以存储2^32 - 1个键值对（40多亿）

    /**
     * 新增 Hash
     *
     * @param hashKey   hash对象的key
     * @param fields    字段集合
     */
    public boolean hashPutAll(String hashKey, Map<String, Object> fields) {
        try {
            redisTemplate.opsForHash().putAll(hashKey, fields);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }


    /**
     * 向指定Hash添加一个键值对
     *
     * @param hashKey       hash对象的key
     * @param field         字段名
     * @param value         字段值
     */
    public boolean hashPutOne(String hashKey, String field, String value) {
        try {
            redisTemplate.opsForHash().put(hashKey, field, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 向指定Hash对象设置一个字段
     *      hashKey或field不存在时才生效
     *
     * @param hashKey       hash对象的key
     * @param field         字段名
     * @param value         字段值
     */
    public boolean hashPutOneIfAbsent(String hashKey, String field, String value) {
        try {
            redisTemplate.opsForHash().putIfAbsent(hashKey, field, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 获取指定Hash对象的value
     *
     * @param hashKey       hash对象的key
     * @return
     */
    public Object hashGetValue(String hashKey, String key) {
        return redisTemplate.opsForHash().get(hashKey, key);
    }

    /**
     * 获取指定Hash对象的所有键值对
     *
     * @param hashKey       hash对象的key
     * @return
     */
    public Map<Object, Object> hashGetAll(String hashKey) {
        return redisTemplate.opsForHash().entries(hashKey);
    }


    /**
     * 删除Hash对象的一个或多个属性
     *
     * @param hashKey       hash对象的key
     * @param fields      属性名
     * @return
     */
    public Long hashDelete(String hashKey, String... fields) {
        return redisTemplate.opsForHash().delete(hashKey, fields);
    }

    /**
     * 查看hash表中指定字段是否存在
     *
     * @param hashKey       hash对象的key
     * @param field
     * @return
     */
    public boolean hashExists(String hashKey, String field) {
        return redisTemplate.opsForHash().hasKey(hashKey, field);
    }

    /**
     * 给哈希表key中的指定字段的整数值加上增量increment
     *
     * @param hashKey       hash对象的key
     * @param field         字段名
     * @param increment
     * @return
     */
    public Long hashIncrementByLong(String hashKey, Object field, long increment) {
        return redisTemplate.opsForHash().increment(hashKey, field, increment);
    }

    /**
     * 给哈希表key中的指定字段的double加上增量increment
     *
     * @param hashKey       hash对象的key
     * @param field         字段名
     * @param delta
     * @return
     */
    public Double hashIncrementByDouble(String hashKey, Object field, double delta) {
        return redisTemplate.opsForHash().increment(hashKey, field, delta);
    }

    /**
     * 获取hash表中存在的所有的key
     *
     * @param hashKey       hash对象的key
     * @return
     */
    public Set<Object> hashFields(String hashKey) {
        return redisTemplate.opsForHash().keys(hashKey);
    }

    /**
     * 获取hash表中存在的所有的Value
     *
     * @param hashKey       hash对象的key
     * @return
     */
    public List<Object> hashValues(String hashKey) {
        return redisTemplate.opsForHash().values(hashKey);
    }

    /**
     * 获取hash表的大小
     *
     * @param hashKey
     * @return
     */
    public Long hashSize(String hashKey) {
        return redisTemplate.opsForHash().size(hashKey);
    }

    // #####################################################【List】#####################################################
    // 字符串列表，按照插入顺序排序。可在表头或表尾插入元素
    // 每个List可以存储2^32 - 1个元素（40多亿）

    /**
     * 头部新增元素
     *
     * @param key
     * @param value
     * @return
     */
    public Boolean listLeftPush(String key, Object value) {
        try {
            redisTemplate.opsForList().leftPush(key, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 头部批量新增元素
     *
     * @param key    List名字
     * @param values
     * @return
     */
    public Boolean listLeftPushAll(String key, Collection<Object> values) {
        try {
            redisTemplate.opsForList().leftPushAll(key, values);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 如果存在List->key, 则设置值到List中的头部
     *
     * @param key   List名字
     * @param value
     * @return
     */
    public Boolean listAddIfPresent(String key, Object value) {
        try {
            redisTemplate.opsForList().leftPushIfPresent(key, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 设置值到List中的尾部
     *
     * @param key   List名字
     * @param value
     * @return
     */
    public Boolean listRightPush(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 批量设置值到List中的尾部
     *
     * @param key    List名字
     * @param values
     * @return
     */
    public Boolean listRightPushAll(String key, Collection<Object> values) {
        try {
            redisTemplate.opsForList().rightPushAll(key, values);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 通过索引去设置List->key中的值
     *
     * @param key
     * @param index
     * @param value
     * @return
     */
    public Boolean listAddByIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }


    /**
     * 根据索引获取list中的值
     *
     * @param key   list名字
     * @param index
     * @return
     */
    public Object listGetByIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 根据索引范围获取list中的值
     *
     * @param key   list名字
     * @param start
     * @param end
     * @return
     */
    public List<Object> listGetByRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 移除并获取列表中第一个元素(如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止)
     *
     * @param key list名字
     * @return
     */
    public Object listLeftPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 移除并获取列表中最后一个元素(如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止)
     *
     * @param key list名字
     * @return
     */
    public Object listRightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 删除集合中值等于value的元素(
     * index=0, 删除所有值等于value的元素;
     * index>0, 从头部开始删除第一个值等于value的元素;
     * index<0, 从尾部开始删除第一个值等于value的元素)
     *
     * @param key
     * @param index
     * @param value
     * @return
     */
    public Long listRemove(String key, long index, Object value) {
        Long removeNum = redisTemplate.opsForList().remove(key, index, value);
        return removeNum;
    }

    // #####################################################【Set】#####################################################
    // 集合成员无序且唯一
    // 每个Set可以存储2^32 - 1个元素（40多亿）

    /**
     * 设置值到Set集合(支持批量)
     *
     * @param key
     * @param value
     * @return
     */
    public Boolean setAdd(String key, Object... value) {
        try {
            redisTemplate.opsForSet().add(key, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 移除Set集合中的值(支持批量)
     *
     * @param key
     * @param values
     * @return 移除的数量
     */
    public long setRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 判断Set中是否存在value
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setIsExist(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 随机集合中的所有元素
     *
     * @param key
     */
    public Set<Object> setGetMembers(String key){
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 随机获取一个元素
     * @param key
     */
    public Object setGetRandomMember(String key){
        return redisTemplate.opsForSet().randomMember(key);
    }

    /**
     * 随机获取count个元素
     * @param key
     * @param count
     * @return
     */
    public List<Object> setGetRandomMembers(String key, long count){
        return redisTemplate.opsForSet().randomMembers(key, count);
    }

    /**
     * 随机弹出一个元素
     * @param key
     */
    public Object setPop(String key){
        return redisTemplate.opsForSet().pop(key);
    }

    /**
     * 获取size
     * @param key
     * @return
     */
    public long setSize(String key){
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 获取多个集合的交集
     *
     * @param key
     * @param otherKeys
     */
    public Set<Object> setGetIntersect(String key, List<String> otherKeys){
        return redisTemplate.opsForSet().intersect(key, otherKeys);
    }

    /**
     * 获取多个集合的交集并存入指定集合
     * @param key
     * @param otherKeys
     * @param destKey
     */
    public void setGetIntersectAndStore(String key, List<String> otherKeys, String destKey){
        redisTemplate.opsForSet().intersectAndStore(key, otherKeys, destKey);
    }

    /**
     * 获取多个集合的并集
     * @param key
     * @param otherKeys
     * @return
     */
    public Set<Object> setGetUnion(String key, List<String> otherKeys){
        return redisTemplate.opsForSet().union(key, otherKeys);
    }

    /**
     * 获取多个集合的并集并存入指定集合
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    public void setGetUnionAndStore(String key, List<String> otherKeys, String destKey){
        redisTemplate.opsForSet().unionAndStore(key, otherKeys, destKey);
    }

    /**
     * 获取多个集合的差集
     * @param key
     * @param otherKeys
     * @return
     */
    public Set<Object> setGetDifference(String key, List<String> otherKeys){
        return redisTemplate.opsForSet().difference(key, otherKeys);
    }

    /**
     * 获取多个集合的差集并存入指定集合
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    public void setGetDifferenceAndStore(String key, List<String> otherKeys, String destKey){
        redisTemplate.opsForSet().differenceAndStore(key, otherKeys, destKey);
    }

    // #####################################################【sortedSet(zSet)】#####################################################
    // 有序集合的成员是唯一的,但分数(score)却可以重复。按照元素的score值由小到大进行排列
    // 每个Set可以存储2^32 - 1个元素（40多亿）


    /**
     * 新增
     *
     * @param key
     * @param value
     * @param score
     * @return
     */
    public Boolean sortedSetAdd(String key, Object value, double score) {
        try {
            redisTemplate.opsForZSet().add(key, value, score);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 删除
     *
     * @param key
     * @param values
     */
    public long sortedSetRemove(String key, Object... values){
        return redisTemplate.opsForZSet().remove(key, values);
    }

    /**
     * 移除指定索引位置处的成员
     * @param key
     * @param start
     * @param end
     * @return
     */
    public long sortedSetRemoveRange(String key, long start, long end){
        return redisTemplate.opsForZSet().removeRange(key, start, end);
    }

    /**
     * 根据Score 范围删除元素
     * @param key
     * @param min
     * @param max
     * @return
     */
    public long sortedSetRemoveRangeByScore(String key, long min, long max){
        return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }

    /**
     * 增加元素的score值，并返回增加后的值
     * @param key
     * @param value
     * @param score
     */
    public Double sortedSetIncrementScore(String key, String value, double score){
        return redisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    /**
     * 获取指定元素的score
     *
     * @param key
     * @param value
     * @return
     */
    public Double sortedSetGetScore(String key, String value){
        return redisTemplate.opsForZSet().score(key, value);
    }


    /**
     * 返回元素在集合的排名,有序集合是按照元素的score值由小到大排列
     * @param key
     * @param value
     */
    public Long sortedSetRank(String key, String value){
        return redisTemplate.opsForZSet().rank(key, value);
    }

    /**
     * 返回元素在集合的排名,按元素的score值由大到小排列
     *
     * @param key
     * @param value
     */
    public Long sortedSetReverseRank(String key, String value){
        return redisTemplate.opsForZSet().reverseRank(key, value);
    }

    /**
     * 获取集合中给定区间的元素
     *      -1 查询所有
     * @param key
     * @param start
     * @param end
     */
    public Set<ZSetOperations.TypedTuple<Object>> sortedSetReverseRangeWithScores(String key, long start, long end){
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
    }

    /**
     * 按照Score值查询集合中的元素，结果从小到大排序
     * @param key
     * @param min
     * @param max
     */
    public Set<Object> sortedSetReverseRangeByScore(String key, long min, long max){
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
    }

    /**
     * 从高到低的排序集中获取分数在最小和最大值之间的元素
     * @param key
     * @param min
     * @param max
     * @param start
     * @param end
     */
    public Set<Object> sortedSetReverseRangeByScore(String key, long min, long max, long start, long end){
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max, start, end);
    }

    /**
     * 根据score值获取集合元素数量
     * @param key
     * @param min
     * @param max
     * @return
     */
    public long sortedSetCount(String key, long min, long max){
        return redisTemplate.opsForZSet().count(key, min, max);
    }

    /**
     * 获取集合的大小
     * @param key
     * @return
     */
    public long sortedSetSize(String key){
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * 获取多个集合的交集
     *
     * @param key
     * @param otherKeys
     */
    public Set<Object> sortedSetGetIntersect(String key, List<String> otherKeys){
        return redisTemplate.opsForSet().intersect(key, otherKeys);
    }

    /**
     * 获取多个集合的交集并存入指定集合
     * @param key
     * @param otherKeys
     * @param destKey
     */
    public void sortedSetGetIntersectAndStore(String key, List<String> otherKeys, String destKey){
        redisTemplate.opsForSet().intersectAndStore(key, otherKeys, destKey);
    }

    /**
     * 获取多个集合的并集
     * @param key
     * @param otherKeys
     * @return
     */
    public Set<Object> sortedSetGetUnion(String key, List<String> otherKeys){
        return redisTemplate.opsForSet().union(key, otherKeys);
    }

    /**
     * 获取多个集合的并集并存入指定集合
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    public void sortedSetGetUnionAndStore(String key, List<String> otherKeys, String destKey){
        redisTemplate.opsForSet().unionAndStore(key, otherKeys, destKey);
    }

    /**
     * 获取多个集合的差集
     * @param key
     * @param otherKeys
     * @return
     */
    public Set<Object> sortedSetGetDifference(String key, List<String> otherKeys){
        return redisTemplate.opsForSet().difference(key, otherKeys);
    }

    /**
     * 获取多个集合的差集并存入指定集合
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    public void sortedSetGetDifferenceAndStore(String key, List<String> otherKeys, String destKey){
        redisTemplate.opsForSet().differenceAndStore(key, otherKeys, destKey);
    }

    // #####################################################【Scan】#####################################################
    // 迭代器

    /**
     * 获取hash 迭代器
     * @param key
     * @param options
     * @return
     */
    public Cursor<Map.Entry<Object, Object>> hashScan(String key, ScanOptions options) {
        if (options == null){
            options = ScanOptions.NONE;
        }
        return redisTemplate.opsForHash().scan(key, options);
    }

    /**
     * 获取Set 迭代器
     * @param key
     * @param options
     * @return
     */
    public Cursor<Object> setScan (String key, ScanOptions options){
        if (options == null){
            options = ScanOptions.NONE;
        }
        return redisTemplate.opsForSet().scan(key, options);
    }

    /**
     * 获取sorted set 迭代器
     * @param key
     * @param options
     * @return
     */
    public Cursor<ZSetOperations.TypedTuple<Object>> sortedSetScan (String key, ScanOptions options){
        if (options == null){
            options = ScanOptions.NONE;
        }
        return redisTemplate.opsForZSet().scan(key, options);
    }


    // #####################################################【经纬度】#####################################################

    /***
     * 将指定的地理空间位置（纬度、经度、名称）添加到指定的key中
     *
     * @param key redis的key
     * @param longitude   经度
     * @param latitude   纬度
     * @param name  该坐标的名称（标识）
     * @return
     */
    public Long geoAdd(String key, double longitude, double latitude, String name) {
//        Long addedNum = redisTemplate.opsForGeo().add("city", new Point(116.405285, 39.904989), "北京");
        Long addedNum = redisTemplate.opsForGeo().add(key, new Point(longitude, latitude), name);
        return addedNum;
    }

    /***
     * 从key里返回所有给定位置元素的位置（经度和纬度）。
     * @param key redis的key
     * @param nameList 坐标名称（标识）的集合
     */
    public List<Point> geoGet(String key, List<String> nameList) {
        List<Point> points = redisTemplate.opsForGeo().position(key, nameList);
        return points;
    }


    /***
     * 【获取两个坐标之间的距离】
     * 根据redis中键名（key）中，名字为 name1 和 name2 两个坐标的距离
     * @param key redis的key
     * @param name1 坐标名称(标识)1
     * @param name2 坐标名称（标识）2
     * @return distance(单位米)
     */
    public double geoGetDistance(String key, String name1, String name2) {
        double distance = redisTemplate.opsForGeo()
                .distance(key, name1, name2, RedisGeoCommands.DistanceUnit.METERS).getValue();
        return distance;
    }


    /***
     * 【获取指定范围内的坐标】
     * 以给定的经纬度为中心画圆， 返回键（key）包含的位置元素当中，
     * 与中心的距离不超过给定最大距离的所有位置元素，并给出所有位置元素与中心的平均距离。
     * @param key redis的key
     * @param longitude   经度
     * @param latitude   纬度
     * @param distance 距离(单位：米)
     * @param count 如果 count > 0 则最多返回count个坐标， 否则返回所有
     * @return
     */
    public GeoResults<RedisGeoCommands.GeoLocation<Object>> geoGetCoordinatesWithinRange(String key,
                                                                                         double longitude,
                                                                                         double latitude,
                                                                                         Integer distance,
                                                                                         Integer count) {
        //以当前坐标为中心画圆，标识当前坐标覆盖的distance的范围， Point(经度, 纬度) Distance(距离量, 距离单位)
        Circle circle = new Circle(new Point(longitude, latitude), new Distance(distance, RedisGeoCommands.DistanceUnit.METERS));
        // 从redis获取的信息包含：距离中心坐标的距离、当前的坐标、并且升序排序，如果count > 0 则只取count个坐标，否则返回所有
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs
                .newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending();
        if (count > 0) {
            args.limit(count);
        }
        GeoResults<RedisGeoCommands.GeoLocation<Object>> radius = redisTemplate.opsForGeo().radius(key, circle, args);
        return radius;
    }

    /***
     * 【获取指定范围内的坐标】
     * 以给定的键（key）中的坐标名字（标识）name为中心画圆， 返回键包含的位置元素当中，
     * 与中心的距离不超过给定最大距离的所有位置元素，并给出所有位置元素与中心的平均距离。
     * @param key redis的key
     * @param name 坐标名称(标识)
     * @param distance 距离
     * @param count 如果 count > 0 则最多返回count个坐标， 否则返回所有
     * @return
     */
    public GeoResults<RedisGeoCommands.GeoLocation<Object>> geoGetCoordinatesWithinRange(String key,
                                                                                         String name,
                                                                                         Integer distance,
                                                                                         Integer count) {
        // 创建距离对象
        Distance distances = new Distance(distance, RedisGeoCommands.DistanceUnit.METERS);
        // 需要从redis获取的参数
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs
                .newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending();
        if (count > 0) {
            args.limit(count);
        }
        GeoResults<RedisGeoCommands.GeoLocation<Object>> radius = redisTemplate.opsForGeo()
                .radius(key, name, distances, args);
        return radius;
    }


}

