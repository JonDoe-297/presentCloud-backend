package com.yunbanke.daoyun.infrastructure.Types;

import lombok.Data;

@Data
public class Sex {
    // male:1, female:0
    private int sex;

    public Sex(int sex) {
        this.sex = sex;
    }
}
