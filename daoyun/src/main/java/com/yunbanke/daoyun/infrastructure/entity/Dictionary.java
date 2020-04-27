package com.yunbanke.daoyun.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Dictionary {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer dic_id;
    // 关键字
    private String dic_keyword;
    // 配置值
    private String dic_value;
    // 详细描述说明
    private String dic_discription;
    // 类型
    private Integer dic_type;
    // 是否为默认值
    private Integer dic_isdefault;

    @Override
    public String toString() {
        return "Dictionary{" +
                "dic_id=" + dic_id +
                ", dic_keyword='" + dic_keyword + '\'' +
                ", dic_value='" + dic_value + '\'' +
                ", dic_discription='" + dic_discription + '\'' +
                ", dic_type=" + dic_type +
                ", dic_isdefault=" + dic_isdefault +
                '}';
    }

    public Integer getDic_id() {
        return dic_id;
    }

    public void setDic_id(Integer dic_id) {
        this.dic_id = dic_id;
    }

    public String getDic_keyword() {
        return dic_keyword;
    }

    public void setDic_keyword(String dic_keyword) {
        this.dic_keyword = dic_keyword;
    }

    public String getDic_value() {
        return dic_value;
    }

    public void setDic_value(String dic_value) {
        this.dic_value = dic_value;
    }

    public String getDic_discription() {
        return dic_discription;
    }

    public void setDic_discription(String dic_discription) {
        this.dic_discription = dic_discription;
    }

    public Integer getDic_type() {
        return dic_type;
    }

    public void setDic_type(Integer dic_type) {
        this.dic_type = dic_type;
    }

    public Integer getDic_isdefault() {
        return dic_isdefault;
    }

    public void setDic_isdefault(Integer dic_isdefault) {
        this.dic_isdefault = dic_isdefault;
    }
}
