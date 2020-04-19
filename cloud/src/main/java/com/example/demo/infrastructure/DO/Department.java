package com.example.demo.infrastructure.DO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Department extends Base {
    private String name;
    @ManyToOne
    private School school;
}
