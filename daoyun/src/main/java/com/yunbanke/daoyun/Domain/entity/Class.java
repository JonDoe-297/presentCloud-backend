package com.yunbanke.daoyun.Domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Class {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer class_id;
    // 班课创建者 对应user_id
    private Integer user_id;
    private String class_name;
    // 班课成员数量
    private Integer class_member;
    // 班课号
    private String class_num;

    @Override
    public String toString() {
        return "Class{" +
                "class_id=" + class_id +
                ", class_creator=" + user_id +
                ", class_name='" + class_name + '\'' +
                ", class_member=" + class_member +
                ", class_num='" + class_num + '\'' +
                '}';
    }

    public Integer getClass_id() {
        return class_id;
    }

    public void setClass_id(Integer class_id) {
        this.class_id = class_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public Integer getClass_member() {
        return class_member;
    }

    public void setClass_member(Integer class_member) {
        this.class_member = class_member;
    }

    public String getClass_num() {
        return class_num;
    }

    public void setClass_num(String class_num) {
        this.class_num = class_num;
    }
}
