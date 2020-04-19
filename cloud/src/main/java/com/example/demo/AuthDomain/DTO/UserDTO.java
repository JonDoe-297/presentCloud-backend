package com.example.demo.AuthDomain.DTO;

import com.example.demo.infrastructure.DO.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    private int id;
    private String name;
    private String nickname;
    private String email;
    private String sno;
    private String phone;
    private int activate;

    private String password;

    public static UserDTO convertFromUser(User user){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
    return userDTO;
    }
}
