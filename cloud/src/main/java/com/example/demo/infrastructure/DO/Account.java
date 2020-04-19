package com.example.demo.infrastructure.DO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Account extends Base {
    private String login_type;
    private String password;
    private String login_username;

    @ManyToOne
    private User user;
}
