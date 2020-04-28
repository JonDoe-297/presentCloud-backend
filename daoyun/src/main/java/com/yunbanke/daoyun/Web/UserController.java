package com.yunbanke.daoyun.Web;

import com.yunbanke.daoyun.infrastructure.Persistence.AccountRepository;
import com.yunbanke.daoyun.infrastructure.Response.CommonReturnType;
import com.yunbanke.daoyun.infrastructure.entity.Account;
import com.yunbanke.daoyun.infrastructure.entity.User;
import com.yunbanke.daoyun.infrastructure.Persistence.UserRepository;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping(path = "user/register")
    public String addUser(String username, String password, Model model) {
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
        return "index";
    }
}
