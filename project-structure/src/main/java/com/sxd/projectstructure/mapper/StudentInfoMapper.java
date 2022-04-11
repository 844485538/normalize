package com.sxd.projectstructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sxd.projectstructure.entity.dto.StudentInfoDO;
import com.sxd.projectstructure.entity.vo.StudentGradeVO;
import org.springframework.stereotype.Repository;

import java.util.List;

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
