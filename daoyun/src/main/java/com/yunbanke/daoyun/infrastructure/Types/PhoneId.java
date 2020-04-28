package com.yunbanke.daoyun.infrastructure.Types;

import lombok.Data;

@Data
public class PhoneId {
    private Long pid;

    public PhoneId(Long pid) {
        this.pid = pid;
    }
}
