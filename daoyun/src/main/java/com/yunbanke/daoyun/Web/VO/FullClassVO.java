package com.yunbanke.daoyun.Web.VO;

import com.yunbanke.daoyun.infrastructure.entity.Class;
import com.yunbanke.daoyun.infrastructure.entity.User;

import java.io.Serializable;

public class FullClassVO implements Serializable {
    private User teacher;
    private Class _class;

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Class get_class() {
        return _class;
    }

    public void set_class(Class _class) {
        this._class = _class;
    }
}
