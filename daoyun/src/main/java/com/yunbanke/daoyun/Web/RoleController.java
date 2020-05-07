package com.yunbanke.daoyun.Web;

import com.yunbanke.daoyun.infrastructure.entity.Role;
import com.yunbanke.daoyun.infrastructure.Persistence.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("/role")
@RestController
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping("/addRole")
    public void addRole(String name, String description) {
        Role role = new Role();
        role.setRole_createtime(new Date());
        role.setRole_description(description);
        role.setRole_name(name);
        roleRepository.saveAndFlush(role);
    }
}
