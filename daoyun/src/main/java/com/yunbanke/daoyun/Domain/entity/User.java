package com.yunbanke.daoyun.Domain.entity;

import lombok.Data;
import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User {

    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    private Integer id;
    private String user_name;
    // 昵称
    private String user_nickname;
    // 生日
    private Date user_birthday;
    // 国家
    private Integer user_nation;
    // 地址
    private String user_address;
    // 所在学校
    private Integer user_school;
    // 学号/工号
    private String user_sno;
    // 是否删除
    private Integer user_isdelete;
    // 用户角色（role_id）
    private Integer role_id;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", user_nickname='" + user_nickname + '\'' +
                ", user_birthday=" + user_birthday +
                ", user_nation=" + user_nation +
                ", user_address='" + user_address + '\'' +
                ", user_school=" + user_school +
                ", user_sno='" + user_sno + '\'' +
                ", user_isdelete=" + user_isdelete +
                ", role_id=" + role_id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public Date getUser_birthday() {
        return user_birthday;
    }

    public void setUser_birthday(Date user_birthday) {
        this.user_birthday = user_birthday;
    }

    public Integer getUser_nation() {
        return user_nation;
    }

    public void setUser_nation(Integer user_nation) {
        this.user_nation = user_nation;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public Integer getUser_school() {
        return user_school;
    }

    public void setUser_school(Integer user_school) {
        this.user_school = user_school;
    }

    public String getUser_sno() {
        return user_sno;
    }

    public void setUser_sno(String user_sno) {
        this.user_sno = user_sno;
    }

    public Integer getUser_isdelete() {
        return user_isdelete;
    }

    public void setUser_isdelete(Integer user_isdelete) {
        this.user_isdelete = user_isdelete;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }
}
