package com.example.demo.infrastructure.DO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "permission")
public class Permission extends Base {
    private String name;
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "permissions")
    private List<Role> roles;
}
