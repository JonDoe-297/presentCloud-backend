package com.example.demo.Web.VO;

import com.example.demo.AuthDomain.DTO.UserDTO;
import com.example.demo.infrastructure.DO.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class UserVO {

    private String name;
    private String nickname;
    private String email;
    private String sno;
    private String phone;

    public static UserVO convertFromUserDTO(UserDTO user){
        if (user == null)
            return null;
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

}
