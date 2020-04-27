package com.yunbanke.daoyun.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Role {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer role_id;
    // 角色名称
    private String role_name;
    // 创建时间
    private Date role_createtime;
    // 角色描述
    private String role_description;

    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", role_name='" + role_name + '\'' +
                ", role_createtime=" + role_createtime +
                ", role_description='" + role_description + '\'' +
                '}';
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public Date getRole_createtime() {
        return role_createtime;
    }

    public void setRole_createtime(Date role_createtime) {
        this.role_createtime = role_createtime;
    }

    public String getRole_description() {
        return role_description;
    }

    public void setRole_description(String role_description) {
        this.role_description = role_description;
    }
}
