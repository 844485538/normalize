package com.normalize.resuful.controller;

import com.normalize.resuful.entity.ResponseTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 李健新
 * @Date 2022/2/25
 * @Description
 *
 *      功能描述
 *
 */
@RestController
@RequestMapping("/template")
public class NormailzeController {

    /**
     * 查询方法
     * @return
     */
    @GetMapping
    public ResponseTemplate getMethod(){
        return ResponseTemplate.success();
    }

    /**
     * 新增方法
     * @return
     */
    public ResponseTemplate PostMethod(){
        return ResponseTemplate.success();
    }

    /**
     * 修改方法
     * @return
     */
    public ResponseTemplate PutMethod(){
        return ResponseTemplate.success();
    }

    /**
     * 删除方法
     * @return
     */
    public ResponseTemplate DeleteMethod(){
        return ResponseTemplate.success();
    }




}
