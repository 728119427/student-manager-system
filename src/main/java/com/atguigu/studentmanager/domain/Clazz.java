package com.atguigu.studentmanager.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Clazz implements Serializable {

    private Integer id;
    private Integer gradeId;
    private String name;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "id=" + id +
                ", gradeId=" + gradeId +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
