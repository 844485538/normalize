package com.sxd.redistemplatedemo.util;

import static org.junit.Assert.*;

import com.alibaba.fastjson.JSONObject;
import com.sxd.redistemplatedemo.entity.DemoEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author 李健新
 * @Date 2022/4/2
 * @Description
 *
 *      key操作方法测试类
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilsTests {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void stringTest() {
        redisUtils.stringSet("string-key", "string-value");
        redisUtils.stringSet("string-chinese", "设置中文");
        redisUtils.stringSet("string-auto-delete", "具有过期时间的key", 20, TimeUnit.SECONDS);
        String str = (String) redisUtils.get("string-chinese");
        System.out.println(str);

        // 设置对象 自动通过FastJson进行序列化
        redisUtils.stringSet("string-object", new DemoEntity(1, "测试", LocalDateTime.now()));
        // 获取string 对象
        DemoEntity demoEntity = JSONObject.parseObject(redisUtils.get("string-object").toString(), DemoEntity.class);
        System.out.println(demoEntity);

        System.out.println(redisUtils.stringGetAndSet("get-and-set", "get-and-set"));

        Map<String, Object> values = new HashMap<>();
        values.put("string-key-new", "1");
        values.put("string-key", "1");
        redisUtils.stringMultiSetIfAbsent(values);
    }

    @Test
    public void hashTest() {
        Map<String, Object> mapValue = new HashMap<>();
        mapValue.put("a", "a");
        mapValue.put("b", "b");
        mapValue.put("c", "c");
        redisUtils.hashPutAll("map-key", mapValue);
        redisUtils.hashPutOne("map-key", "d", "d");
        redisUtils.hashPutOneIfAbsent("map-keys", "e", "new");
        System.out.println(redisUtils.hashGetValue("map-keys", "e"));
        redisUtils.hashGetAll("map-key").forEach(
                (key, value) -> {
                    System.out.println(key + "-" + value);
                }
        );
    }

    @Test
    public void listTest(){
        List<Object> listValue = new LinkedList<>();
        listValue.add("1");
        listValue.add("2");
        redisUtils.listLeftPush("list-key", "3");
        redisUtils.listLeftPushAll("list-key", listValue);
    }


}
