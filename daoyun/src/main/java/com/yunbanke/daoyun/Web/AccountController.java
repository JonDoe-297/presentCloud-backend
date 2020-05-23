package com.yunbanke.daoyun.Web;

import com.alibaba.fastjson.JSONObject;
import com.yunbanke.daoyun.Web.VO.ResultMap;
import com.yunbanke.daoyun.Web.VO.UserInfoVO;
import com.yunbanke.daoyun.infrastructure.Persistence.RoleRepository;
import com.yunbanke.daoyun.infrastructure.Persistence.UserRepository;
import com.yunbanke.daoyun.infrastructure.Util.JwtUtil;
import com.yunbanke.daoyun.infrastructure.Util.common.ResponseBean;
import com.yunbanke.daoyun.infrastructure.entity.Account;
import com.yunbanke.daoyun.infrastructure.Persistence.AccountRepository;
import com.yunbanke.daoyun.infrastructure.entity.Role;
import com.yunbanke.daoyun.infrastructure.entity.User;
import com.yunbanke.daoyun.infrastructure.exception.CustomUnauthorizedException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

//@Controller
@RestController
//@CrossOrigin
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    ResultMap resultMap;


    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    /**
     * @param jsonObject 用户账号（邮箱、手机号等）
     * @return 登录成功
     */
//    @PostMapping(path = "/login")
//    public ResultMap login(@RequestBody JSONObject jsonObject) {
//        String username = jsonObject.getString("username");
//        String password = jsonObject.getString("password");
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        token.setRememberMe(true);
//        try {
//            subject.login(token);
//            System.out.println("登录成功");
//            return resultMap.success().code(200).token(JwtUtil.createToken(username));
//        } catch (AuthenticationException | UnsupportedEncodingException error) {
//            System.out.println("登录失败");
//            return  resultMap.fail().code(401).message("账号或密码错误");
//        }
//    }
    @PostMapping(path = "/login")
    public ResponseBean login(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String isEmail = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String isPhone = "^[1][34578]\\d{9}$";
        Account account;
        if (username.matches(isEmail)) {
            account = accountRepository.findAccountByLoginemail(username);
        } else if (username.matches(isPhone)) {
            account = accountRepository.findAccountByLoginphone(username);
        } else {
            account = accountRepository.findAccountByLoginaccount(username);
        }
        if (account == null) {
            throw new CustomUnauthorizedException("该帐号不存在");
        }
        ByteSource salt = ByteSource.Util.bytes(username);
        String newPsd = new SimpleHash("MD5", password, salt, 3).toHex();
        if (newPsd.equals(account.getLoginpasswd())) {
            String currentTimeMillis = String.valueOf(System.currentTimeMillis());
            String token = JwtUtil.sign(username, currentTimeMillis);
            httpServletResponse.setHeader("Authorization", token);
            httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");

            return new ResponseBean(HttpStatus.OK.value(), "登录成功(Login Success.)", UserInfoVO.convertFromUser(account.getUser()));
        } else {
            throw new CustomUnauthorizedException("帐号或密码错误");
        }

    }

    @GetMapping("/getInfo")
    public ResponseBean getInfo() {
        Subject subject = SecurityUtils.getSubject();
        // 登录了返回true
        if (subject.isAuthenticated()) {
            String token = subject.getPrincipal().toString();
            String username = JwtUtil.getClaim(token, "account");
            //System.out.println(username);
            Account account = accountRepository.findAccountByLoginphone(username);
            return new ResponseBean(HttpStatus.OK.value(), "您已经登录了(You are already logged in)", UserInfoVO.convertFromUser(account.getUser()));
        } else {
            return new ResponseBean(HttpStatus.OK.value(), "你是游客(You are guest)", subject.getPrincipal().toString());
        }
    }

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    @PostMapping(path = "/register")
    public ResponseBean register(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String name = jsonObject.getString("name");
        String role = jsonObject.getString("role");
        String sno = jsonObject.getString("sno");
        User user = new User();
        Account account = accountRepository.findAccountByLoginphone(username);
        if (account != null) {
            return new ResponseBean(HttpStatus.OK.value(), "账号存在", null);
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
        //System.out.println("注册成功");
        return new ResponseBean(HttpStatus.OK.value(), "注册成功", null);
    }

}
