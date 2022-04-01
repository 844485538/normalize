package com.sxd.projectstructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxd.projectstructure.entity.DO.StudentInfoDO;
import com.sxd.projectstructure.entity.VO.StudentGradeVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author LiJianxin
 *
 *      学生信息
 */
@Repository
public interface StudentInfoMapper extends BaseMapper<StudentInfoDO> {

    /**
     * 获取学生成绩
     *
     * @return
     */
    List<StudentGradeVO> listStudentGrade();

    /**
     * 分页查询
     *
     * @param page 分页参数
     * @return
     */
    IPage<StudentGradeVO> pageStudentGrade(IPage<StudentGradeVO> page);

}
