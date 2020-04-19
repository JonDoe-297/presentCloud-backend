package com.example.demo.infrastructure.DO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class School extends Base {
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
    private List<Department> departments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
    private List<User> userList;
}
