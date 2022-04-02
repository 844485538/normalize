package com.sxd.redistemplatedemo.entity;

import java.time.LocalDateTime;

/**
 * @Author 李健新
 * @Date 2022/4/2
 * @Description
 */
public class DemoEntity {

    private Integer id;

    private String name;

    private LocalDateTime createTime;

    public DemoEntity(Integer id, String name, LocalDateTime createTime) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
    }

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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DemoEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
