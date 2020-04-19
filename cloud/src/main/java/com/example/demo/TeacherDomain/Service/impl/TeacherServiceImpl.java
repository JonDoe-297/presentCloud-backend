package com.example.demo.TeacherDomain.Service.impl;

import com.example.demo.AuthDomain.DTO.UserDTO;
import com.example.demo.TeacherDomain.Service.TeacherService;
import com.example.demo.Web.VO.ClassVO;
import com.example.demo.infrastructure.DO.Class;
import com.example.demo.infrastructure.DO.User;
import com.example.demo.infrastructure.Mapper.ClassMapper;
import com.example.demo.infrastructure.Repository.ClassRepository;
import com.example.demo.infrastructure.Repository.UserRepository;
import com.example.demo.infrastructure.Response.CommonReturnType;
import com.example.demo.infrastructure.Utils.CnoFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    ClassRepository classRepository;
    @Autowired
    UserRepository userRepository;
    //shiro的权限拦截注释，表示用户必须拥有教师角色才能执行该方法。逻辑不完整，应该在前端直接不显示没有对应的操作。
    @RequiresRoles("teacher")
    @Override
    public CommonReturnType createClass(String name) {
        Class c = new Class();
        UserDTO userDTO = (UserDTO)SecurityUtils.getSubject().getPrincipal();
        User user = userRepository.getUserByEmail(userDTO.getEmail());
        c.setName(name);
        c.setCreator(user);
        //班课号用当前时间的年月日时秒加上几位随机数表示。
        c.setCno(CnoFactory.Code(user.getId()));
        classRepository.saveAndFlush(c);
        return CommonReturnType.create(ClassVO.convertFromClass(c));
    }
}
