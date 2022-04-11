package com.sxd.projectstructure.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxd.projectstructure.entity.po.StudentInfoPO;
import com.sxd.projectstructure.entity.vo.StudentGradeVO;
import com.sxd.projectstructure.mapper.StudentInfoMapper;
import com.sxd.projectstructure.service.StudentInfoService;
import com.sxd.projectstructure.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 李健新
 * @Date 2022/3/31
 * @Description
 *
 *      学生信息Service
 */
@Service
public class StudentInfoServiceImpl implements StudentInfoService {

    @Autowired
    private StudentInfoMapper studentInfoMapper;


    /**
     * 创建学生
     *
     * @param studentInfoPO
     */
    @Override
    public void insertStudentInfo(StudentInfoPO studentInfoPO) {
        studentInfoPO.setCreateTime(DateTimeUtil.now());
        studentInfoPO.setUpdateTime(DateTimeUtil.now());
        studentInfoMapper.insert(studentInfoPO);
    }

    /**
     * 删除学生
     *
     * @param id
     */
    @Override
    public void deleteStudentInfo(Integer id) {
        studentInfoMapper.deleteById(id);
    }

    /**
     * 修改学生
     *
     * @param studentInfoPO
     * @return
     */
    @Override
    public void updateStudentInfo(StudentInfoPO studentInfoPO) {
        studentInfoPO.setUpdateTime(DateTimeUtil.now());
        studentInfoMapper.updateById(studentInfoPO);
    }

    /**
     * 查询学生信息
     *
     * @param id
     * @return
     */
    @Override
    public StudentInfoPO getStudentInfo(Integer id) {
        return studentInfoMapper.selectById(id);
    }

    /**
     * 列表查询
     *
     * @return
     */
    @Override
    public List<StudentGradeVO> listStudentInfo() {
        return studentInfoMapper.listStudentGrade();
    }

    /**
     * 分页查询
     *
     * @return
     */
    @Override
    public IPage<StudentGradeVO> pageStudentInfo(Integer currentPage, Integer pageSize) {
        Page<StudentGradeVO> page = new Page<>(currentPage, pageSize);
        return studentInfoMapper.pageStudentGrade(page);
    }
}
