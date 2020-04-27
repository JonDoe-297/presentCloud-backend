package com.yunbanke.daoyun.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

// 签到表
@Entity
public class Checkin {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer checkin_id;
    // 是否签到
    private Integer checkin_is;
    // 签到日期
    private Date checkin_date;
    // 签到用户
    private Integer user_id;
    private String class_num;

    @Override
    public String toString() {
        return "Info{" +
                "checkin_id=" + checkin_id +
                ", checkin_is=" + checkin_is +
                ", checkin_date=" + checkin_date +
                ", checkin_user_id='" + user_id + '\'' +
                ", checkin_class='" + class_num + '\'' +
                '}';
    }

    public Integer getCheckin_id() {
        return checkin_id;
    }

    public void setCheckin_id(Integer checkin_id) {
        this.checkin_id = checkin_id;
    }

    public Integer getCheckin_is() {
        return checkin_is;
    }

    public void setCheckin_is(Integer checkin_is) {
        this.checkin_is = checkin_is;
    }

    public Date getCheckin_date() {
        return checkin_date;
    }

    public void setCheckin_date(Date checkin_date) {
        this.checkin_date = checkin_date;
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
}
