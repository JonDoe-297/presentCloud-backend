package com.yunbanke.daoyun.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// 用户权限表
@Entity
public class Access {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer access_id;
    private String access_name;
    private String access_detail;
    // 权限状态
    private Integer access_state;

    @Override
    public String toString() {
        return "Access{" +
                "access_id=" + access_id +
                ", access_name='" + access_name + '\'' +
                ", access_detail='" + access_detail + '\'' +
                ", access_state='" + access_state + '\'' +
                '}';
    }

    public Integer getAccess_id() {
        return access_id;
    }

    public void setAccess_id(Integer access_id) {
        this.access_id = access_id;
    }

    public String getAccess_name() {
        return access_name;
    }

    public void setAccess_name(String access_name) {
        this.access_name = access_name;
    }

    public String getAccess_detail() {
        return access_detail;
    }

    public void setAccess_detail(String access_detail) {
        this.access_detail = access_detail;
    }

    public Integer getAccess_state() {
        return access_state;
    }

    public void setAccess_state(Integer access_state) {
        this.access_state = access_state;
    }
}
