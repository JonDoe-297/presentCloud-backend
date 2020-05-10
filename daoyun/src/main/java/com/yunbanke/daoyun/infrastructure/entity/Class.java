package com.yunbanke.daoyun.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Class implements Serializable {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "class_id")
    private Integer classid;
    // 班课创建者 对应user_id
    @Column(name = "user_id")
    private Integer userid;
    @Column(name = "class_name")
    private String classname;
    // 班课成员数量
    @Column(name = "class_member")
    private Integer classmember;
    // 班课号
    @Column(name = "class_num")
    private String classnum;

    @ManyToMany
    @JoinTable(name="class_student", joinColumns = @JoinColumn(name = "class_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
//    @JsonBackReference
    private List<User> studentList;

    @Override
    public String toString() {
        return "Class{" +
                "class_id=" + classid +
                ", class_creator=" + userid +
                ", class_name='" + classname + '\'' +
                ", class_member=" + classmember +
                ", class_num='" + classnum + '\'' +
                '}';
    }

    public List<User> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<User> studentList) {
        this.studentList = studentList;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Integer getClassmember() {
        return classmember;
    }

    public void setClassmember(Integer classmember) {
        this.classmember = classmember;
    }

    public String getClassnum() {
        return classnum;
    }

    public void setClassnum(String classnum) {
        this.classnum = classnum;
    }
}
