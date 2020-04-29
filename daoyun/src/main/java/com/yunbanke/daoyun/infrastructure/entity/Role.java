package com.yunbanke.daoyun.infrastructure.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Role implements Serializable {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer role_id;
    // 角色名称
    private String role_name;
    // 创建时间
    private Date role_createtime;
    // 角色描述
    private String role_description;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_Id")})
    private List<User> userList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_access", joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "access_id")})
    private List<Access> accessList;


    @Transient
    public Set<String> getAccessName() {
        Set<String> set = new HashSet<>();
        List<Access> accessList = getAccessList();
        for (Access access : accessList) {
            set.add(access.getAccess_name());
        }
        return set;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Access> getAccessList() {
        return accessList;
    }

    public void setAccessList(List<Access> accessList) {
        this.accessList = accessList;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", role_name='" + role_name + '\'' +
                ", role_createtime=" + role_createtime +
                ", role_description='" + role_description + '\'' +
                '}';
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public Date getRole_createtime() {
        return role_createtime;
    }

    public void setRole_createtime(Date role_createtime) {
        this.role_createtime = role_createtime;
    }

    public String getRole_description() {
        return role_description;
    }

    public void setRole_description(String role_description) {
        this.role_description = role_description;
    }
}
