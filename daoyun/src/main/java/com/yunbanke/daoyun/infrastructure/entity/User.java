package com.yunbanke.daoyun.infrastructure.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    @Column(name = "user_id")
    private Integer userid;
    @Column(name = "user_name")
    private String username;
    // 昵称
    @Column(name = "user_nickname")
    private String usernickname;
    // 生日
    @Column(name = "user_birthday")
    private Date userbirthday;
    // 国家
    @Column(name = "user_nation")
    private Integer usernation;
    // 地址
    @Column(name = "user_address")
    private String useraddress;
    // 所在学校
    @Column(name = "user_school")
    private Integer userschool;
    // 学号/工号
    @Column(name = "user_sno")
    private String usersno;
    // 是否删除
    @Column(name = "user_isdelete")
    private Integer userisdelete;
    // 用户角色（role_id）
//    @Column(name = "role_id")
//    private Integer roleid;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_Id")})
    private List<Role> roleList;
    // 用户登录信息
    @OneToOne(cascade = CascadeType.ALL) // 关系维护端
//    @JoinTable(name = "user_account",
//        joinColumns = @JoinColumn(name = "user_id"),
//        inverseJoinColumns = @JoinColumn(name = "account_id"))
    private Account account;

    @ManyToMany(mappedBy = "studentList")
    private List<Class> classList;


    @Transient
    public Set<String> getRolesName() {
        Set<String> roles = new LinkedHashSet<>();
        for (Role role : roleList) {
            roles.add(role.getRole_name());
        }
        return roles;
    }

    public List<Class> getClassList() {
        return classList;
    }

    public void setClassList(List<Class> classList) {
        this.classList = classList;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + userid +
                ", user_name='" + username + '\'' +
                ", user_nickname='" + usernickname + '\'' +
                ", user_birthday=" + userbirthday +
                ", user_nation=" + usernation +
                ", user_address='" + useraddress + '\'' +
                ", user_school=" + userschool +
                ", user_sno='" + usersno + '\'' +
                ", user_isdelete=" + userisdelete +
                '}';
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernickname() {
        return usernickname;
    }

    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname;
    }

    public Date getUserbirthday() {
        return userbirthday;
    }

    public void setUserbirthday(Date userbirthday) {
        this.userbirthday = userbirthday;
    }

    public Integer getUsernation() {
        return usernation;
    }

    public void setUsernation(Integer usernation) {
        this.usernation = usernation;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public Integer getUserschool() {
        return userschool;
    }

    public void setUserschool(Integer userschool) {
        this.userschool = userschool;
    }

    public String getUsersno() {
        return usersno;
    }

    public void setUsersno(String usersno) {
        this.usersno = usersno;
    }

    public Integer getUserisdelete() {
        return userisdelete;
    }

    public void setUserisdelete(Integer userisdelete) {
        this.userisdelete = userisdelete;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
