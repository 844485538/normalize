package com.sxd.projectstructure.controller;

import com.sxd.projectstructure.entity.VO.ResponseTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 李健新
 * @Date 2022/2/25
 * @Description
 *
 *         RESTFul-API 示例类
 *
 */
@RestController
@RequestMapping("/demo")
public class RestFulApiController {

    /**
     * 查询方法
     * 
     * @return
     */
    @GetMapping("")
    public ResponseTemplate getMethod(@RequestParam Integer id) {
        return ResponseTemplate.success(xxx.get(id));
    }

    /**
     * 新增方法
     * 
     * @return
     */
    @PostMapping("")
    public ResponseTemplate insertMethod(@RequestBody DemoDO demoDO) {
        xxx.save(demoDO);
        return ResponseTemplate.success();
    }

    /**
     * 修改方法
     * 
     * @return
     */
    @PutMapping("/{id}")
    public ResponseTemplate updateMethod(@PathVariable Integer id, @RequestBody DemoDO demoDO) {
        xxx.update(demoDO);
        return ResponseTemplate.success();
    }

    /**
     * 删除方法
     * 
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseTemplate deleteMethod(@PathVariable Integer id) {
        xxx.delete(id);
        return ResponseTemplate.success();
    }

}
