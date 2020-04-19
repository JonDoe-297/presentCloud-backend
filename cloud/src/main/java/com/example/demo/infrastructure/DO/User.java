package com.example.demo.infrastructure.DO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class User extends Base {
    private String name;
    private String nickname;
    private String email;
    private String sno;
    private String phone;
    private int activate = 1;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")},
        inverseJoinColumns = {@JoinColumn(name = "role_Id")})
    private List<Role> roleList;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Account> passwords;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creator")
    private List<Class> classList;

    @ManyToOne
    private School school;

    @Transient
    public Set<String> getRolesName(){
        Set<String> roles = new LinkedHashSet<>();
        for (Role role: roleList) {
            roles.add(role.getName());
        }
        return roles;
    }

}
