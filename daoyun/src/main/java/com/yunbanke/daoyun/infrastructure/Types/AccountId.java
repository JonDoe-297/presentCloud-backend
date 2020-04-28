package com.yunbanke.daoyun.infrastructure.Types;

import lombok.Data;

@Data
public class AccountId {
    private Long id;

    AccountId(Long id){this.id = id;}
}
