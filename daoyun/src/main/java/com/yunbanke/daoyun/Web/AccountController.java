package com.yunbanke.daoyun.Web;

import com.yunbanke.daoyun.infrastructure.entity.Account;
import com.yunbanke.daoyun.infrastructure.Persistence.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        String isEmail = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String isPhone = "^[1][34578]\\d{9}$";
        // 如果是手机号
        if(account.matches(isPhone)) {
            List<Account> accounts = accountRepository.findAccountsByLoginphoneAndLoginpasswd(account, password);
            if(accounts.isEmpty()){
                System.out.println("登录失败");
            }
            else if(accounts.size() == 1){
                System.out.println("登录成功！");
            } else {
                System.out.println("Internal Wrong： 存在多个Account。");
            }
        }
        // Email
        else if(account.matches(isEmail)) {
            List<Account> accounts = accountRepository.findAccountsByLoginemailAndLoginpasswd(account, password);
            if(accounts.isEmpty()){
                System.out.println("登录失败");
            }
            else if(accounts.size() == 1){
                System.out.println("登录成功！");
            } else {
                System.out.println("Internal Wrong： 存在多个Account。");
            }
        }
        // Account
        else {
            List<Account> accounts = accountRepository.findAccountsByLoginaccountAndLoginpasswd(account, password);
            if(accounts.isEmpty()){
                System.out.println("登录失败");
            }
            else if(accounts.size() == 1){
                System.out.println("登录成功！");
            } else {
                System.out.println("Internal Wrong： 存在多个Account。");
            }
        }
    }


}
