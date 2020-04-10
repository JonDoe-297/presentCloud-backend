package com.yunbanke.daoyun.Web;

import com.yunbanke.daoyun.Persistence.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/account")
@RestController
@CrossOrigin
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    /**
     * @param account 用户账号（邮箱、手机号等）
     * @param password 密码
     * @return 1 登录成功
     */
    @GetMapping(path = "/login")
    public void login(@RequestParam String account, @RequestParam String password){
        System.out.println("account: " + account);
        System.out.println("password: " + password);
    }
}
