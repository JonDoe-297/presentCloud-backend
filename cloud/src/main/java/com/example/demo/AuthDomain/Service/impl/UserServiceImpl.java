package com.example.demo.AuthDomain.Service.impl;


import com.example.demo.AuthDomain.Service.UserService;
import com.example.demo.AuthDomain.DTO.UserDTO;
import com.example.demo.Web.VO.UserVO;
import com.example.demo.infrastructure.DO.Role;
import com.example.demo.infrastructure.DO.User;
import com.example.demo.infrastructure.DO.Account;
import com.example.demo.infrastructure.Repository.PasswordRepository;
import com.example.demo.infrastructure.Repository.RoleRepository;
import com.example.demo.infrastructure.Repository.UserRepository;
import com.example.demo.infrastructure.Response.CommonReturnType;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordRepository passwordRepository;
    @Override
    public CommonReturnType Register(String username, String password, int role_type) {
        //可以通过判断username的格式决定查找手机或者邮箱
        List<User> Users = userRepository.findByEmail(username);
        if (!Users.isEmpty()){
            return CommonReturnType.create("用户存在");
        }
        ByteSource salt = ByteSource.Util.bytes(username);
        String newPsd = new SimpleHash("MD5", password, salt,3).toHex();
        User user = new User();
        user.setEmail(username);
        List<Role> roles = roleRepository.findRolesById(role_type);
        user.setRoleList(roles);
        user = userRepository.saveAndFlush(user);
        Account account = new Account();
        account.setLogin_type("email");
        account.setLogin_username(username);
        account.setPassword(newPsd);
        account.setUser(user);
        passwordRepository.save(account);
        UserDTO userDTO = UserDTO.convertFromUser(user);
        UserVO userVO = UserVO.convertFromUserDTO(userDTO);
        return CommonReturnType.create(userVO);
    }
}
