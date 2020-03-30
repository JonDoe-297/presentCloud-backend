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
@AllArgsConstructor
@NoArgsConstructor
public class Dict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dict_id;
    @NotNull
    private String dict_keyword;
    @NotNull
    private String dict_value;
    private String description;
    private int dict_type;
    private int dict_isDefault;
}
