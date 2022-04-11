package com.sxd.projectstructure.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sxd.projectstructure.entity.DO.StudentInfoDO;
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
     * @param studentInfoDO
     */
    void insertStudentInfo(StudentInfoDO studentInfoDO);

    /**
     * 删除学生信息
     *
     * @param id
     */
    void deleteStudentInfo(Integer id);

    /**
     * 修改学生信息
     *
     * @param studentInfoDO
     * @return
     */
    void updateStudentInfo(StudentInfoDO studentInfoDO);

    /**
     * 查询学生信息
     *
     * @param id
     * @return
     */
    StudentInfoDO getStudentInfo(Integer id);

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
