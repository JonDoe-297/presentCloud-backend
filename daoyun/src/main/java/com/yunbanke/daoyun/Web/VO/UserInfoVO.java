package com.yunbanke.daoyun.Web.VO;

import com.yunbanke.daoyun.infrastructure.entity.Role;
import com.yunbanke.daoyun.infrastructure.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter

public class UserInfoVO implements Serializable {
    private Integer userid;
    private String username;
    // 昵称
    private String usernickname;
    // 生日
    private Date userbirthday;
    // 国家
    private Integer usernation;
    // 地址
    private String useraddress;
    // 所在学校
    private String userschool;

    private String userDepartment;

    private List<Role> roleList;

    public UserInfoVO() {
    }

    public static UserInfoVO convertFromUser(User user) {
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(user, userInfoVO);
        return userInfoVO;
    }

}
