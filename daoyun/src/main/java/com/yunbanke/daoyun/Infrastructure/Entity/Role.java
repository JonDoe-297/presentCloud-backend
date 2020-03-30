package com.yunbanke.daoyun.Infrastructure.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int role_id;
    @NotNull
    private String role_name;
    @CreatedDate//根据创作操作自动填写当前时间
    private Date role_create_date;
    private String role_description;
    @JsonIgnore
    //角色和权限多对多关系，一个角色拥有多个权限，一个权限被多个角色拥有， mappedBy决定了两个表之间那个是主表
    @ManyToMany( cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "role_permission", joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private List<Permission> permissionList;

    @OneToMany(mappedBy = "role", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<User> users;

    // Transient 表示这不是数据库中的表项
    @Transient
    public Set<String> getPermissionName(){
        Set<String> set = new HashSet<>();
        List<Permission> permissionList = getPermissionList();
        for (Permission permission : permissionList){
            set.add(permission.getPermission_name());
        }
        return set;
    }
}
