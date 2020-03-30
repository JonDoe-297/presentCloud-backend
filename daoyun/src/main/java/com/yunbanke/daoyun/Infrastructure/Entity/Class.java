package com.yunbanke.daoyun.Infrastructure.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int class_id;
    @NotNull
    private String class_creator; //班课创建者账号
    @NotNull
    private String class_name;
    private int class_number; //班课人数
    @NotNull
    private String class_num; // 班课号

}
