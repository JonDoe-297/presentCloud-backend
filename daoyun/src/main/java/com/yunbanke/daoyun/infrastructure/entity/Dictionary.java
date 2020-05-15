package com.yunbanke.daoyun.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dictionary {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long dicId;
    private String dicCode;
    private String dicName;
    private String dicDiscription;
    private long dicDefaultId;

    @Override
    public String toString() {
        return "{" +
                "'dicId'=" + dicId +","+
                "'dicCode'='" + dicCode +"',"+
                "'dicName'='" + dicName +"',"+
                "'dicDiscription'='" + dicDiscription  +"',"+
                "'dicDefaultId'=" + dicDefaultId +
                "}";
    }

    public long getDicId() {
        return dicId;
    }

    public void setDicId(long dicId) {
        this.dicId = dicId;
    }


    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }


    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }


    public String getDicDiscription() {
        return dicDiscription;
    }

    public void setDicDiscription(String dicDiscription) {
        this.dicDiscription = dicDiscription;
    }


    public long getDicDefaultId() {
        return dicDefaultId;
    }

    public void setDicDefaultId(long dicDefaultId) {
        this.dicDefaultId = dicDefaultId;
    }

}
