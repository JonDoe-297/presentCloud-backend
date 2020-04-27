package com.yunbanke.daoyun.infrastructure.entity;

import javax.persistence.*;

// 账户表
@Entity
public class Account {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "account_id")
    private Integer accountid;
    // 对应用户id
//    @Column(name = "user_id")
//    private Integer userid;
    // 登录账号
    @Column(name = "login_account")
    private String loginaccount;
    // 登录邮箱
    @Column(name = "login_email")
    private String loginemail;
    // 登录手机号
    @Column(name = "login_phone")
    private String loginphone;
    // 登录密码（md5）
    @Column(name = "login_passwd")
    private String loginpasswd;
    @OneToOne(mappedBy = "account")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + accountid +
                ", login_account='" + loginaccount + '\'' +
                ", login_email='" + loginemail + '\'' +
                ", login_phone='" + loginphone + '\'' +
                ", login_passwd='" + loginpasswd + '\'' +
                '}';
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

//    public Integer getUserid() {
//        return userid;
//    }
//
//    public void setUserid(Integer userid) {
//        this.userid = userid;
//    }

    public String getLoginaccount() {
        return loginaccount;
    }

    public void setLoginaccount(String loginaccount) {
        this.loginaccount = loginaccount;
    }

    public String getLoginemail() {
        return loginemail;
    }

    public void setLoginemail(String loginemail) {
        this.loginemail = loginemail;
    }

    public String getLoginphone() {
        return loginphone;
    }

    public void setLoginphone(String loginphone) {
        this.loginphone = loginphone;
    }

    public String getLoginpasswd() {
        return loginpasswd;
    }

    public void setLoginpasswd(String loginpasswd) {
        this.loginpasswd = loginpasswd;
    }
}
