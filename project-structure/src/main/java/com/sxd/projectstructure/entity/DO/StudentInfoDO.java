package com.sxd.projectstructure.entity.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sxd.projectstructure.config.jsr.InsertDO;
import com.sxd.projectstructure.config.jsr.UpdateDO;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @Author 李健新
 * @Date 2022/3/31
 * @Description
 *
 *          学生信息
 */
@TableName(value = "student_info")
public class StudentInfoDO {

    @NotNull(message = "ID不可为空", groups = {UpdateDO.class})
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    @NotEmpty(message = "菜单名称不可为空", groups = {InsertDO.class, UpdateDO.class})
    private String name;

    /**
     * 年龄
     */
    @NotNull(message = "年龄不可为空", groups = {InsertDO.class, UpdateDO.class})
    private Integer age;

    /**
     * 身份证号
     */
    @NotEmpty(message = "身份证号不可为空", groups = {InsertDO.class, UpdateDO.class})
    @TableField(value = "id_card")
    private String idCard;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
