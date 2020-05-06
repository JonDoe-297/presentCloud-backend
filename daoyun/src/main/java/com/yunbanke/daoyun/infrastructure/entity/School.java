package com.yunbanke.daoyun.infrastructure.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "school")
public class School implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "school_id")
    private Integer school_id;

    @Column(name = "school_name")
    private String school_name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "school_department", joinColumns = {@JoinColumn(name = "school_id")},
            inverseJoinColumns = {@JoinColumn(name = "department_id")})
    private List<Department> departmentList;

    public School() {
    }

    @Override
    public String toString() {
        return "School{" +
                "school_id=" + school_id +
                ", school_name='" + school_name + '\'' +
                '}';
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public Integer getSchool_id() {
        return school_id;
    }

    public void setSchool_id(Integer school_id) {
        this.school_id = school_id;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }
}
