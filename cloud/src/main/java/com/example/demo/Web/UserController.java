package com.example.demo.Web;

import com.example.demo.AuthDomain.Service.UserService;
import com.example.demo.AuthDomain.DTO.UserDTO;
import com.example.demo.Web.VO.UserVO;
import com.example.demo.infrastructure.Response.CommonReturnType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping(value = "/register")
    public @ResponseBody
    //CommonReturnType
    String
    Register(String username, String password, int role_type) throws JsonProcessingException {
        CommonReturnType commonReturnType = userService.Register(username, password, role_type);
        System.out.println(commonReturnType.getData());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(commonReturnType);
        

    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping({"/","/index"})
    public String  toIndex(){
        return "index";
    }
    @RequestMapping ("/login")

    public
    //@ResponseBody
    String Login(String username, String password, Model model){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        try{

            subject.login(token);
            UserDTO userDTO = (UserDTO)subject.getPrincipal();
            System.out.println(userDTO.getPassword());
            UserVO userVO = new UserVO();
            UserVO.convertFromUserDTO(userDTO);
            model.addAttribute(CommonReturnType.create(userVO));
            return "index";
        }catch (UnknownError e){
            return "login";
        }
    }

}
