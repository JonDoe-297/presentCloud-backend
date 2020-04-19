package com.example.demo.infrastructure.DO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "role")
public class Role extends Base {
    private String name;
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_Id")})
    private List<User> userList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission", joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private List<Permission> permissions;

    @Transient
    public Set<String> getPermissionName(){
        Set<String> set = new HashSet<>();
        List<Permission> permissionList = getPermissions();
        for (Permission permission : permissionList){
            set.add(permission.getName());
        }
        return set;
    }
    @Transient
    public Set<String> getUserName(){
        Set<String> set = new HashSet<>();
        List<User> userList = getUserList();
        for (User user : userList){
            set.add(user.getName());
        }
        return set;
    }
}
