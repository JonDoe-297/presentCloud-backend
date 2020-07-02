package com.yunbanke.daoyun.Web;

import com.yunbanke.daoyun.infrastructure.Persistence.AccessRepository;
import com.yunbanke.daoyun.infrastructure.Util.common.ResponseBean;
import com.yunbanke.daoyun.infrastructure.entity.Access;
import com.yunbanke.daoyun.infrastructure.entity.Role;
import com.yunbanke.daoyun.infrastructure.Persistence.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/role")
@RestController
//@CrossOrigin
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    AccessRepository accessRepository;

    @RequestMapping("/addRole")
    public ResponseBean addRole(@RequestParam String name, @RequestParam String description) {
        Role role = new Role();
        role.setRole_createtime(new Date());
        role.setRole_description(description);
        role.setRole_name(name);
        roleRepository.saveAndFlush(role);
        return new ResponseBean(HttpStatus.OK.value(), "创建成功", null);
    }

    @RequestMapping("/updateRole")
    public ResponseBean updateRole(@RequestParam String name, @RequestParam String description) {
        List<Role> roles = roleRepository.getRolesByName(name);
        Role role = roles.get(0);
        role.setRole_description(description);
        role.setRole_name(name);
        roleRepository.saveAndFlush(role);
        return new ResponseBean(HttpStatus.OK.value(), "修改成功", null);
    }

    @RequestMapping("/deleteRole")
    public ResponseBean deleteRole(@RequestParam String name) {
        List<Role> roles = roleRepository.getRolesByName(name);
        Role role = roles.get(0);
        roleRepository.delete(role);
        return new ResponseBean(HttpStatus.OK.value(), "删除成功", null);
    }

    @RequestMapping("/addAccess")
    public ResponseBean addAccess(@RequestParam String roleId, @RequestParam String accessId) {
        Role role = roleRepository.getOne(Integer.valueOf(roleId));
        Access access = accessRepository.getOne(Integer.valueOf(accessId));
        role.getAccessList().add(access);
        roleRepository.saveAndFlush(role);
        return new ResponseBean(HttpStatus.OK.value(), "权限添加成功", null);
    }
}
