package com.yunbanke.daoyun.infrastructure.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

// 签到表
@Entity
public class Checkin implements Serializable {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "checkin_id")
    private Integer checkinid;
    // 是否签到
    @Column(name = "checkin_is")
    private Integer checkinis;
    // 签到日期
    @Column(name = "checkin_date")
    private Date checkindate;
    // 签到用户
    @Column(name = "user_id")
    private Integer userid;
    @Column(name = "class_num")
    private String classnum;

    @Override
    public String toString() {
        return "Info{" +
                "checkin_id=" + checkinid +
                ", checkin_is=" + checkinis +
                ", checkin_date=" + checkindate +
                ", checkin_user_id='" + userid + '\'' +
                ", checkin_class='" + classnum + '\'' +
                '}';
    }

    public Integer getCheckinid() {
        return checkinid;
    }

    public void setCheckinid(Integer checkinid) {
        this.checkinid = checkinid;
    }

    public Integer getCheckinis() {
        return checkinis;
    }

    public void setCheckinis(Integer checkinis) {
        this.checkinis = checkinis;
    }

    public Date getCheckindate() {
        return checkindate;
    }

    public void setCheckindate(Date checkindate) {
        this.checkindate = checkindate;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getClassnum() {
        return classnum;
    }

    public void setClassnum(String classnum) {
        this.classnum = classnum;
    }
}
