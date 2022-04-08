package com.sxd.demo.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashMap;
import java.util.Map;

/**
 *  @Author 李健新
 *  @Date 2022/4/8
 *  @Description
 *
 *          对应请求接口示例在controller下DemoController
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateUtilsTest {

    @Autowired
    private RestTemplateUtils restTemplateUtils;

    @Test
    public void sendGetTest(){
        String url = "http://localhost:8080/get";
        Map<String, String> param = new HashMap<>();
        param.put("name", "姓名");
        ResponseEntity responseEntity = restTemplateUtils.sendGet(url, param);
        System.out.println(responseEntity.toString());
    }

    @Test
    public void sendPostTest(){
        String url = "http://localhost:8080/post";
        Map<String, String> param = new HashMap<>();
        param.put("name", "姓名");
        param.put("age", "12");
        ResponseEntity responseEntity = restTemplateUtils.sendPost(url, param);
        System.out.println(responseEntity.toString());
    }

    @Test
    public void sendPostHeaderTest(){
        String url = "http://localhost:8080/header";
        Map<String, String> param = new HashMap<>();
        param.put("name", "姓名");
        param.put("age", "12");
        Map<String, String> header = new HashMap<>();
        header.put("header", "header-value");
        ResponseEntity responseEntity = restTemplateUtils.sendPost(url,header, param);
        System.out.println(responseEntity.toString());
    }

}