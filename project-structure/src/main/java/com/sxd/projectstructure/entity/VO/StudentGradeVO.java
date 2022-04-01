package com.sxd.projectstructure.entity.VO;

/**
 * @Author 李健新
 * @Date 2022/4/1
 * @Description
 *
 *      学生成绩对象
 */
public class StudentGradeVO {

    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 科目
     */
    private String subject;

    /**
     * 成绩
     */
    private Integer grade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "StudentGradeVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", grade=" + grade +
                '}';
    }
}
