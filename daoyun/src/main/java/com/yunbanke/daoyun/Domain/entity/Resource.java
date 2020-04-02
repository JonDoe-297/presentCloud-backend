package com.yunbanke.daoyun.Domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Resource {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer resource_id;
    // 资源号
    private String resource_num;
    // 发布者
    private Integer user_id;
    // 所属班课号
    private String class_num;
    // 资源名称
    private String resource_name;
    // 资源类型
    private String resource_type;
    // 发布时间
    private Date resource_s_date;
    // 失效时间
    private Date resource_e_date;

    @Override
    public String toString() {
        return "Resource{" +
                "resource_id=" + resource_id +
                ", resource_num='" + resource_num + '\'' +
                ", account_id=" + user_id +
                ", class_num='" + class_num + '\'' +
                ", resource_name='" + resource_name + '\'' +
                ", resource_type='" + resource_type + '\'' +
                ", resource_s_date=" + resource_s_date +
                ", resource_e_date=" + resource_e_date +
                '}';
    }

    public Integer getResource_id() {
        return resource_id;
    }

    public void setResource_id(Integer resource_id) {
        this.resource_id = resource_id;
    }

    public String getResource_num() {
        return resource_num;
    }

    public void setResource_num(String resource_num) {
        this.resource_num = resource_num;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getClass_num() {
        return class_num;
    }

    public void setClass_num(String class_num) {
        this.class_num = class_num;
    }

    public String getResource_name() {
        return resource_name;
    }

    public void setResource_name(String resource_name) {
        this.resource_name = resource_name;
    }

    public String getResource_type() {
        return resource_type;
    }

    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }

    public Date getResource_s_date() {
        return resource_s_date;
    }

    public void setResource_s_date(Date resource_s_date) {
        this.resource_s_date = resource_s_date;
    }

    public Date getResource_e_date() {
        return resource_e_date;
    }

    public void setResource_e_date(Date resource_e_date) {
        this.resource_e_date = resource_e_date;
    }
}
