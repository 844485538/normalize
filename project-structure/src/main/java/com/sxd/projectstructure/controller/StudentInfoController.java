package com.sxd.projectstructure.controller;

import com.sxd.projectstructure.config.jsr.InsertDO;
import com.sxd.projectstructure.config.jsr.UpdateDO;
import com.sxd.projectstructure.entity.po.StudentInfoPO;
import com.sxd.projectstructure.entity.vo.ResponseTemplate;
import com.sxd.projectstructure.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 李健新
 * @Date 2022/4/1
 * @Description
 */
@RestController
@RequestMapping("/student/info")
public class StudentInfoController {

    @Autowired
    private StudentInfoService studentInfoService;

    /**
     * 新增学生信息
     *
     * @param studentInfoPO 学生信息
     * @return
     */
    @PostMapping
    public ResponseTemplate insertStudentInfo(@Validated(value = InsertDO.class) @RequestBody StudentInfoPO studentInfoPO) {
        studentInfoService.insertStudentInfo(studentInfoPO);
        return ResponseTemplate.success();
    }

    @DeleteMapping("/{id}")
    public ResponseTemplate deleteStudentInfo(@PathVariable Integer id) {
        studentInfoService.deleteStudentInfo(id);
        return ResponseTemplate.success();
    }

    @PutMapping
    public ResponseTemplate updateStudentInfo(@Validated(value = UpdateDO.class) @RequestBody StudentInfoPO studentInfoPO) {
        studentInfoService.updateStudentInfo(studentInfoPO);
        return ResponseTemplate.success();
    }

    @GetMapping
    public ResponseTemplate getStudentInfo(@RequestParam Integer id) {
        return ResponseTemplate.success(studentInfoService.getStudentInfo(id));
    }

    @GetMapping("/list")
    public ResponseTemplate listStudentInfo() {
        return ResponseTemplate.success(studentInfoService.listStudentInfo());
    }

    @GetMapping("/page")
    public ResponseTemplate pageStudentInfo(@RequestParam(required = false, defaultValue = "1") Integer currentPage,
                                            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return ResponseTemplate.success(studentInfoService.pageStudentInfo(currentPage, pageSize));
    }

}
