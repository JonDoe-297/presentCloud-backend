package com.yunbanke.daoyun.infrastructure.Types;

import lombok.Data;

@Data
public class AccountType {
    //账户类型， 1代表管理员，2代表教师，3代表学生
    private int type;

    public AccountType(int type) {
        this.type = type;
    }
}
