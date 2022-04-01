package com.sxd.projectstructure.controller;

import com.sxd.projectstructure.entity.DO.StudentInfoDO;
import com.sxd.projectstructure.entity.VO.ResponseTemplate;
import com.sxd.projectstructure.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param studentInfoDO 学生信息
     * @return
     */
    @PostMapping
    public ResponseTemplate insertStudentInfo(@RequestBody StudentInfoDO studentInfoDO) {
        studentInfoService.insertStudentInfo(studentInfoDO);
        return ResponseTemplate.success();
    }

    @DeleteMapping("/{id}")
    public ResponseTemplate deleteStudentInfo(@PathVariable Integer id) {
        studentInfoService.deleteStudentInfo(id);
        return ResponseTemplate.success();
    }

    @PutMapping
    public ResponseTemplate updateStudentInfo(@RequestBody StudentInfoDO studentInfoDO) {
        studentInfoService.updateStudentInfo(studentInfoDO);
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
