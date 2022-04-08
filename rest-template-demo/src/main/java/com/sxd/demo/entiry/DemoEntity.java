package com.sxd.demo.entiry;

/**
 * @Author 李健新
 * @Date 2022/4/8
 * @Description
 */
public class DemoEntity {

    private String name;

    private Integer age;

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

    @Override
    public String toString() {
        return "DemoEntity{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
