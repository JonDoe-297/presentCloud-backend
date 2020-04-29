package com.yunbanke.daoyun.Web;

import com.yunbanke.daoyun.infrastructure.Persistence.UserRepository;
import com.yunbanke.daoyun.infrastructure.Response.CommonReturnType;
import com.yunbanke.daoyun.infrastructure.entity.Account;
import com.yunbanke.daoyun.infrastructure.Persistence.AccountRepository;
import com.yunbanke.daoyun.infrastructure.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@Controller
//@RestController
//@CrossOrigin
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    /**
     * @param username 用户账号（邮箱、手机号等）
     * @param password 密码
     * @return 1 登录成功
     */
    @PostMapping(path = "/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        try {
            subject.login(token);
            return "index";
        } catch (UnknownError error) {
            return "login";
        }

//        String isEmail = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
//        String isPhone = "^[1][34578]\\d{9}$";
//        // 如果是手机号
//        if(account.matches(isPhone)) {
//            List<Account> accounts = accountRepository.findAccountsByLoginphoneAndLoginpasswd(account, password);
//            if(accounts.isEmpty()){
//                System.out.println("登录失败");
//            }
//            else if(accounts.size() == 1){
//                System.out.println("登录成功！");
//            } else {
//                System.out.println("Internal Wrong： 存在多个Account。");
//            }
//        }
//        // Email
//        else if(account.matches(isEmail)) {
//            List<Account> accounts = accountRepository.findAccountsByLoginemailAndLoginpasswd(account, password);
//            if(accounts.isEmpty()){
//                System.out.println("登录失败");
//            }
//            else if(accounts.size() == 1){
//                System.out.println("登录成功！");
//            } else {
//                System.out.println("Internal Wrong： 存在多个Account。");
//            }
//        }
//        // Account
//        else {
//            List<Account> accounts = accountRepository.findAccountsByLoginaccountAndLoginpasswd(account, password);
//            if(accounts.isEmpty()){
//                System.out.println("登录失败");
//            }
//            else if(accounts.size() == 1){
//                System.out.println("登录成功！");
//            } else {
//                System.out.println("Internal Wrong： 存在多个Account。");
//            }
//        }
    }

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    @PostMapping(path = "/register")
    public String register(String username, String password, Model model) {
        User user = new User();
        Account account = accountRepository.findAccountByLoginphone(username);
        if (account != null) {
            model.addAttribute("msg", "账号存在");
            return "register";
        }
        account = new Account();
        account.setLoginphone(username);
        //用用户的电话作为加密密码的盐
        ByteSource salt = ByteSource.Util.bytes(username);
        String newPsd = new SimpleHash("MD5", password, salt, 3).toHex();
        account.setLoginpasswd(newPsd);
        user.setAccount(account);
        user = userRepository.saveAndFlush(user);

        model.addAttribute("data", CommonReturnType.create(user));
        return "login";
    }
}
