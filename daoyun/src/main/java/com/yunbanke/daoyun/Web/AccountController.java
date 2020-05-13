package com.yunbanke.daoyun.Web;

import com.alibaba.fastjson.JSONObject;
import com.yunbanke.daoyun.infrastructure.Persistence.RoleRepository;
import com.yunbanke.daoyun.infrastructure.Persistence.UserRepository;
import com.yunbanke.daoyun.infrastructure.Response.CommonReturnType;
import com.yunbanke.daoyun.infrastructure.entity.Account;
import com.yunbanke.daoyun.infrastructure.Persistence.AccountRepository;
import com.yunbanke.daoyun.infrastructure.entity.Role;
import com.yunbanke.daoyun.infrastructure.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
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

    @Autowired
    private RoleRepository roleRepository;


    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    /**
     * @param jsonObject 用户账号（邮箱、手机号等）
     * @return 登录成功
     */
    @PostMapping(path = "/login")
    public String login(@RequestBody JSONObject jsonObject, Model model) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            System.out.println(user.getUsername());
            model.addAttribute("name", user.getUsername());
            return "index";
        } catch (AuthenticationException error) {
            model.addAttribute("msg", "账户或密码错误");
            return "login";
        }
    }

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    @PostMapping(path = "/register")
    public String register(@RequestBody JSONObject jsonObject, Model model) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String name = jsonObject.getString("name");
        String role = jsonObject.getString("role");
        String sno = jsonObject.getString("sno");
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
        List<Role> roleList = roleRepository.getRolesByName(role);
        user.setAccount(account);
        user.setUsersno(sno);
        user.setUsername(name);
        user.setRoleList(roleList);
        userRepository.saveAndFlush(user);

        model.addAttribute("meg", "注册成功");
        //System.out.println("注册成功");
        return "login";
    }

}
