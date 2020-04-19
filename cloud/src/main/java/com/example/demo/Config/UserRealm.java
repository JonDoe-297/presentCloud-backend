package com.example.demo.Config;

import com.example.demo.AuthDomain.DTO.UserDTO;
import com.example.demo.infrastructure.DO.Role;
import com.example.demo.infrastructure.DO.User;
import com.example.demo.infrastructure.DO.Account;
import com.example.demo.infrastructure.Repository.PasswordRepository;
import com.example.demo.infrastructure.Repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordRepository passwordRepository;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("开始授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        UserDTO currentUser = (UserDTO) SecurityUtils.getSubject().getPrincipal();
        User user = userRepository.getUserByEmail(currentUser.getEmail());
        info.setRoles(user.getRolesName());
        List<Role> roles = user.getRoleList();
        Set<String> permissions = new HashSet<>();
        for (Role role : roles){
            permissions.addAll(role.getPermissionName());

        }
        info.setStringPermissions(permissions);
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = authenticationToken.getPrincipal().toString();
        User user = userRepository.getUserByEmail(username);
        Account account = passwordRepository.getUserPasswordByLogin_username(username);
        if (account == null){
            throw new AuthenticationException("用户不存在！");
        }
        UserDTO userDTO = UserDTO.convertFromUser(user);
        System.out.println("邮箱"+ user.getEmail());
        userDTO.setPassword(account.getPassword());
        System.out.println("开始认证");
        String realName = getName();
        ByteSource salt = ByteSource.Util.bytes(account.getLogin_username());
        return new SimpleAuthenticationInfo(
                userDTO,
                //authenticationToken.getCredentials(),
                userDTO.getPassword(),
                salt,
                realName
        );

    }

}
