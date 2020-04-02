package com.yunbanke.daoyun.Domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// 账户表
@Entity
public class Account {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer account_id;
    // 对应用户id
    private Integer user_id;
    // 登录账号
    private String login_account;
    // 登录邮箱
    private String login_email;
    // 登录手机号
    private String login_phone;
    // 登录密码（md5）
    private String login_passwd;

    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", user_id=" + user_id +
                ", login_account='" + login_account + '\'' +
                ", login_email='" + login_email + '\'' +
                ", login_phone='" + login_phone + '\'' +
                ", login_passwd='" + login_passwd + '\'' +
                '}';
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getLogin_account() {
        return login_account;
    }

    public void setLogin_account(String login_account) {
        this.login_account = login_account;
    }

    public String getLogin_email() {
        return login_email;
    }

    public void setLogin_email(String login_email) {
        this.login_email = login_email;
    }

    public String getLogin_phone() {
        return login_phone;
    }

    public void setLogin_phone(String login_phone) {
        this.login_phone = login_phone;
    }

    public String getLogin_passwd() {
        return login_passwd;
    }

    public void setLogin_passwd(String login_passwd) {
        this.login_passwd = login_passwd;
    }
}
