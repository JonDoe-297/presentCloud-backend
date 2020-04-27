package com.yunbanke.daoyun.infrastructure.Config;

import com.yunbanke.daoyun.infrastructure.Persistence.AccountRepository;
import com.yunbanke.daoyun.infrastructure.entity.Account;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    AccountRepository accountRepository;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = authenticationToken.getPrincipal().toString();
        Account account;
        String isEmail = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String isPhone = "^[1][34578]\\d{9}$";
        if (username.matches(isEmail)) {
            account = accountRepository.findAccountByLoginemail(username);
        } else if (username.matches(isPhone)) {
            account = accountRepository.findAccountByLoginphone(username);
        } else {
            account = accountRepository.findAccountByLoginaccount(username);
        }
        if (account == null) {
            throw new AuthenticationException("用户不存在");
        }
        String realName = getName();
        ByteSource salt = ByteSource.Util.bytes(account.getLoginphone());
        return new SimpleAuthenticationInfo(
                account.getUser(),
                account.getLoginpasswd(),
                salt,
                realName
        );

    }
}
