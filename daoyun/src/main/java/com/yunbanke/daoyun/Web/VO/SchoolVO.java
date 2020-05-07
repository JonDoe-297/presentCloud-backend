package com.yunbanke.daoyun.Web.VO;

import java.io.Serializable;

public class SchoolVO implements Serializable {
    private int id;
    private String name;

    public SchoolVO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public SchoolVO() {
    }

    @Override
    public String toString() {
        return "SchoolVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
