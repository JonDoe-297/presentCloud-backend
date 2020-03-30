package com.yunbanke.daoyun.Infrastructure.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Permission {
    @Id
    @GeneratedValue
    private int permission_id;
    @NotNull
    private String permission_name;
    @NotNull
    private int permission_state;
    private String permission_detail;
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(name = "role_permission", joinColumns = {@JoinColumn(name = "permission_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles;

    @Transient
    public Set<String> getRoleName(){
        Set<String> set = new HashSet<>();
        List<Role> roles = getRoles();
        for (Role role : roles){
            set.add(role.getRole_name());
        }
        return set;
    }


}
