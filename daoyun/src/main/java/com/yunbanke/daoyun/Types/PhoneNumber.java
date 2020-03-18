package com.yunbanke.daoyun.Types;

import lombok.Data;

@Data
public class PhoneNumber {
    private Long P_number;

    public PhoneNumber(Long p_number) {
        P_number = p_number;
    }
}
