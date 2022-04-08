package com.sxd.redistemplatedemo.util;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author 李健新
 * @Date 2022/4/2
 * @Description
 *
 *      性能测试
 *
 *      redis 中执行info命令 可获取内存使用情况
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PerformanceTests {

    private static final String PREFIX = "performance:test:";

    /**
     * 数据量
     */
    private static final int COUNT = 100000;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void jsonTest() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "测试");
        jsonObject.put("age", 18);
        jsonObject.put("id_card", "222222222222222222");
        jsonObject.put("address", "XX省XX市XXX区XXXXXXXXXXXX");
        long start = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            redisUtils.stringSet(PREFIX + i, jsonObject);
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 100);
    }

    @Test
    public void infoTest() {
        RedisCallback<Object> redisCallback = new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bytes = new byte[]{};
                return connection.serverCommands().info();
            }
        };
        Object object = redisTemplate.execute(redisCallback);
        System.out.println(JSONObject.toJSONString(object));
    }


}
