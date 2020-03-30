package com.yunbanke.daoyun.Infrastructure.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
// 对代码的自动填写
@Data  //get(), set()
@NoArgsConstructor//无参构造器
@AllArgsConstructor//全参构造器
public class Checkin {
    @Id  //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键自动增加
    private int checkin_id;
    @NotNull
    private int checkin_is; //是否签到
    private Date checkin_date; //签到时间
    @NotNull
    private String checkin_account; //签到用户账号
    @NotNull
    private String checkin_class; //签到班课号
}

