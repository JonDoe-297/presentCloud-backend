package com.yunbanke.daoyun.infrastructure.Config;

import com.yunbanke.daoyun.infrastructure.Filter.JwtToken;
import com.yunbanke.daoyun.infrastructure.Persistence.AccountRepository;
import com.yunbanke.daoyun.infrastructure.Util.JwtUtil;
import com.yunbanke.daoyun.infrastructure.Util.common.StringUtil;
import com.yunbanke.daoyun.infrastructure.entity.Account;
import com.yunbanke.daoyun.infrastructure.entity.Role;
import com.yunbanke.daoyun.infrastructure.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
        info.setRoles(currentUser.getRolesName());
        List<Role> roles = currentUser.getRoleList();
        Set<String> accesses = new HashSet<>();
        for (Role role : roles) {
            accesses.addAll(role.getAccessName());
        }
        info.setStringPermissions(accesses);
        return info;
    }


    //用户认证
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        String username = authenticationToken.getPrincipal().toString();
//        Account account;
//        String isEmail = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
//        String isPhone = "^[1][34578]\\d{9}$";
//        if (username.matches(isEmail)) {
//            account = accountRepository.findAccountByLoginemail(username);
//        } else if (username.matches(isPhone)) {
//            account = accountRepository.findAccountByLoginphone(username);
//        } else {
//            account = accountRepository.findAccountByLoginaccount(username);
//        }
//        if (account == null) {
//            throw new AuthenticationException("用户不存在");
//        }
//        String realName = getName();
//        ByteSource salt = ByteSource.Util.bytes(account.getLoginphone());
//        return new SimpleAuthenticationInfo(
//                account.getUser(),
//                account.getLoginpasswd(),
//                salt,
//                realName
//        );
//
//    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getPrincipal();
        String username = JwtUtil.getClaim(token, "account");
        if (StringUtil.isBlank(username)) {
            throw new AuthenticationException("Token中帐号为空(The account in Token is empty.)");
        }
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
            throw new AuthenticationException("该帐号不存在(The account does not exist.)");
        }
        if (JwtUtil.verify(token)) {
            return new SimpleAuthenticationInfo(token, token, "userRealm");
        }
        throw new AuthenticationException("Token已过期(Token expired or incorrect.)");
    }
}
