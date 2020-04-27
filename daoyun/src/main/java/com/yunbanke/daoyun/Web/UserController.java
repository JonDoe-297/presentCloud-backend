package com.yunbanke.daoyun.Web;

import com.yunbanke.daoyun.infrastructure.entity.Account;
import com.yunbanke.daoyun.infrastructure.entity.User;
import com.yunbanke.daoyun.infrastructure.Persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/addUser")
    public void addUser(){
        User user = new User();
        Account account = new Account();
        user.setUseraddress("测试——用户地址。");
        user.setUserbirthday(new Date());
        user.setUserisdelete(0);
        user.setUsername("张翊卓");
        user.setUsernation(86);
        user.setUsernickname("zyz");
        user.setUserschool(88);
        user.setUsersno("190322222");
        account.setLoginaccount("123213231");
        account.setLoginemail("text@test.com");
        account.setLoginpasswd("testpassword");
        account.setLoginphone("13111111111");
        user.setAccount(account);
        userRepository.save(user);
    }
}
