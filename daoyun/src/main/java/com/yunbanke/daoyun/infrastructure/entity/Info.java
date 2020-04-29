package com.yunbanke.daoyun.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Info implements Serializable {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer info_id;
    // 发布信息者id
    private Integer user_id;
    // 查看权限
    private Integer role_id;
    // 通知内容
    private String info_content;
    // 通知类型
    private Integer info_type;

    @Override
    public String toString() {
        return "Info{" +
                "info_id=" + info_id +
                ", info_account_id=" + user_id +
                ", info_role_id=" + role_id +
                ", info_content='" + info_content + '\'' +
                ", info_type=" + info_type +
                '}';
    }

    public Integer getInfo_id() {
        return info_id;
    }

    public void setInfo_id(Integer info_id) {
        this.info_id = info_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getInfo_content() {
        return info_content;
    }

    public void setInfo_content(String info_content) {
        this.info_content = info_content;
    }

    public Integer getInfo_type() {
        return info_type;
    }

    public void setInfo_type(Integer info_type) {
        this.info_type = info_type;
    }
}
