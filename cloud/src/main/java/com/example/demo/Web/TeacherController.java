package com.example.demo.Web;

import com.example.demo.AuthDomain.DTO.UserDTO;
import com.example.demo.TeacherDomain.Service.TeacherService;
import com.example.demo.Web.VO.ClassVO;
import com.example.demo.Web.VO.UserVO;
import com.example.demo.infrastructure.DO.Class;
import com.example.demo.infrastructure.DO.User;
import com.example.demo.infrastructure.Repository.UserRepository;
import com.example.demo.infrastructure.Response.CommonReturnType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.Subject;
import java.util.Optional;

@Controller
@RequestMapping(name = "/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;
    @Autowired
    UserRepository userRepository;


    @GetMapping("/teacher/createClass")
    public
    //@ResponseBody
    //CommonReturnType
    String
    createClass(String name, Model model){
        CommonReturnType commonReturnType = teacherService.createClass(name);
        ClassVO classVO = (ClassVO)commonReturnType.getData();
        model.addAttribute("cno", classVO.getCno());
        return "index";
    }

//    @RequiresRoles("teacher")
    @RequestMapping("/teacher/toCreateClass")
    public String goCreateClass(){
        return "create";
    }


    @GetMapping("update")
    public void update(int id, String name, String nickname){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            user.get().setName(name);
            user.get().setNickname(nickname);
            userRepository.save(user.get());
        }
    }
}
