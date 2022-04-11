package com.sxd.projectstructure.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sxd.projectstructure.entity.po.StudentInfoPO;
import com.sxd.projectstructure.entity.vo.StudentGradeVO;

import java.util.List;

/**
 * @Author 李健新
 * @Date 2022/3/31
 * @Description
 *
 *      学生信息查询Service
 */
public interface StudentInfoService {

    /**
     * 创建学生信息
     *
     * @param studentInfoPO
     */
    void insertStudentInfo(StudentInfoPO studentInfoPO);

    /**
     * 删除学生信息
     *
     * @param id
     */
    void deleteStudentInfo(Integer id);

    /**
     * 修改学生信息
     *
     * @param studentInfoPO
     * @return
     */
    void updateStudentInfo(StudentInfoPO studentInfoPO);

    /**
     * 查询学生信息
     *
     * @param id
     * @return
     */
    StudentInfoPO getStudentInfo(Integer id);

    /**
     * 列表查询
     *
     * @return
     */
    List<StudentGradeVO> listStudentInfo();

    /**
     * 分页查询
     *
     * @param currentPage 当前页
     * @param pageSize    每页大小
     * @return
     */
    IPage<StudentGradeVO> pageStudentInfo(Integer currentPage, Integer pageSize);

}
