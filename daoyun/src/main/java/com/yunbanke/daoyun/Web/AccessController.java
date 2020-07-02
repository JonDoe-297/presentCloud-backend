package com.yunbanke.daoyun.Web;

import com.yunbanke.daoyun.infrastructure.Persistence.AccessRepository;
import com.yunbanke.daoyun.infrastructure.Util.common.ResponseBean;
import com.yunbanke.daoyun.infrastructure.entity.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/access")
@RestController
public class AccessController {
    @Autowired
    AccessRepository accessRepository;

    @GetMapping("/addAccess")
    ResponseBean addAccess(@RequestParam String name, @RequestParam String description) {
        Access access = new Access();
        access.setAccess_name(name);
        access.setAccess_detail(description);
        access.setAccess_state(1);
        accessRepository.saveAndFlush(access);
        return new ResponseBean(HttpStatus.OK.value(), "创建权限成功", null);
    }

    @GetMapping("/deleteAccess")
    ResponseBean deleteAccess(@RequestParam String name) {
        Access access = accessRepository.findAccessByAccess_name(name);
        accessRepository.delete(access);
        return new ResponseBean(HttpStatus.OK.value(), "删除成功", null);
    }

}
