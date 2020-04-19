package com.example.demo.Web;

import com.example.demo.infrastructure.DO.Role;
import com.example.demo.infrastructure.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/add")
    public void add(String name, String detail){
        Role role = new Role();
        role.setName(name);
        role.setDescription(detail);
        role.setCreate_date(new Date());
        roleRepository.save(role);
    }

    @GetMapping("/update")
    public void update(int id, String name, String detail){
        Role role = roleRepository.findById(id);
        role.setName(name);
        role.setDescription(detail);
        role.setModify_date(new Date());
        roleRepository.save(role);
    }

    @GetMapping("/delete")
    public void delete(int id){
        roleRepository.deleteById(id);
    }

    @GetMapping("/getAll")
    public void getAll(){
        List<Role> roles = roleRepository.findAll();
    }
}
