package com.yunbanke.daoyun.infrastructure.Types;

import lombok.Data;

@Data
public class AccountNumber {
    private Long number;

    public AccountNumber(Long number) {
        this.number = number;
    }

}
