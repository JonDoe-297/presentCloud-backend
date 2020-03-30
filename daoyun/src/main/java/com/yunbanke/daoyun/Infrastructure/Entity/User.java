package com.yunbanke.daoyun.Infrastructure.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    @NotNull
    private String user_name;
    private String user_nickname;
    @NotNull
    private String user_password;
    private String user_phone;
    @NotNull
    //@Email // 必须是邮箱格式
    private String user_account; //登录账号，暂定邮箱
    @NotNull
    private int is_activate = 1; //是否活跃，1：为活跃 0：为软删除 默认活跃， 可以在后面设为0 需要邮箱认证后激活
    @NotNull
    private String user_son;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    private Role role;



}
