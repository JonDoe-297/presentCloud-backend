package com.yunbanke.daoyun.Web.VO;

import java.io.Serializable;
import java.util.Date;

public class CheckinResultVO implements Serializable {
    String name;
    int chenkinIs;
    Date checkinDate;
    String classNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChenkinIs() {
        return chenkinIs;
    }

    public void setChenkinIs(int chenkinIs) {
        this.chenkinIs = chenkinIs;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
}
