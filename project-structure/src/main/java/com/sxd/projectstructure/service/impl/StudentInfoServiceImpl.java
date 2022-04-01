package com.sxd.projectstructure.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxd.projectstructure.entity.DO.StudentInfoDO;
import com.sxd.projectstructure.entity.VO.StudentGradeVO;
import com.sxd.projectstructure.mapper.StudentInfoMapper;
import com.sxd.projectstructure.service.StudentInfoService;
import com.sxd.projectstructure.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
     * @param studentInfoDO
     */
    @Override
    public void insertStudentInfo(StudentInfoDO studentInfoDO) {
        studentInfoDO.setCreateTime(DateTimeUtil.now());
        studentInfoDO.setUpdateTime(DateTimeUtil.now());
        studentInfoMapper.insert(studentInfoDO);
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
     * @param studentInfoDO
     * @return
     */
    @Override
    public void updateStudentInfo(StudentInfoDO studentInfoDO) {
        studentInfoDO.setUpdateTime(DateTimeUtil.now());
        studentInfoMapper.updateById(studentInfoDO);
    }

    /**
     * 查询学生信息
     *
     * @param id
     * @return
     */
    @Override
    public StudentInfoDO getStudentInfo(Integer id) {
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
        IPage<StudentGradeVO> result =
                studentInfoMapper.pageStudentGrade(page);
        return result;
    }
}
