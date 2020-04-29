package com.yunbanke.daoyun.infrastructure.entity;


import javax.persistence.*;
import java.util.Date;

// 签到信息表
@Entity
public class CheckinInfo {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "checkin_info_id")
    private Integer checkininfoid;
    // 签到课程号
    @Column(name = "class_num")
    private String classnum;
    // 签到起始时间
    @Column(name = "start_time")
    private Date starttime;
    // 签到结束时间
    @Column(name = "end_time")
    private Date endtime;
    @Column(name = "code")
    // 签到密码 (可为空)
    private String code;
    // 是否有效
    @Column(name = "is_valid")
    private Integer isvalid;

    public Integer getCheckininfoid() {
        return checkininfoid;
    }

    public void setCheckininfoid(Integer checkininfoid) {
        this.checkininfoid = checkininfoid;
    }

    public String getClassnum() {
        return classnum;
    }

    public void setClassnum(String classnum) {
        this.classnum = classnum;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }
}
