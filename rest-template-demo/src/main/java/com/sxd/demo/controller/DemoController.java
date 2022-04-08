package com.sxd.demo.controller;

import com.sxd.demo.entiry.DemoEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 李健新
 * @Date 2022/4/8
 * @Description
 */
@RestController
public class DemoController {

    @GetMapping("/get")
    public void get(@RequestParam String name){
        System.out.println(name);
    }

    @GetMapping("/post")
    public void post(@RequestBody DemoEntity demoEntity){
        System.out.println(demoEntity);
    }

    @GetMapping("/header")
    public void header(@RequestHeader String header, @RequestBody DemoEntity demoEntity){
        System.out.println(header);
    }

}
