package com.yunbanke.daoyun.infrastructure.Types;

import lombok.Data;

@Data
public class Name {
    private String name;

    public Name(String name) {
        this.name = name;
    }
}
