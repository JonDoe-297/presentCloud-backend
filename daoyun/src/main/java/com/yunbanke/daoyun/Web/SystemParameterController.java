package com.yunbanke.daoyun.Web;


import com.yunbanke.daoyun.Service.SystemParameterService;
import com.yunbanke.daoyun.Web.VO.RetResponse;
import com.yunbanke.daoyun.infrastructure.entity.Dictionary;
import com.yunbanke.daoyun.infrastructure.entity.SystemParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/systemParameter")
@RestController
@CrossOrigin
public class SystemParameterController {

    @Autowired
    private SystemParameterService systemParameterService;

    @PostMapping(path = "/add")
    public RetResponse add(String name, String parameter){
        int res= systemParameterService.add(name,parameter);

        if(res==-1)
            return new RetResponse("2001", "添加系统参数失败，name已经存在。");
        return new RetResponse("200", "添加系统参数成功");
    }

    @PostMapping(path = "/del")
    public RetResponse del(int sys_id){
        int res= systemParameterService.del(sys_id);
        if(res==-1)
            return new RetResponse("2001", "删除系统参数失败，sys_id不存在。");
        return new RetResponse("200", "删除系统参数成功");
    }

    @PostMapping(path = "/edit")
    public RetResponse edit(int sys_id,String name,String parameter){
        int res= systemParameterService.edit(sys_id, name, parameter);

        if(res==-1)
            return new RetResponse("2001", "修改系统参数失败，sys_id不存在。");
        return new RetResponse("200", "修改系统参数成功");
    }

    @PostMapping(path = "/findAll")
    public RetResponse<List<SystemParameter>> findAll(){
        List<SystemParameter> list = systemParameterService.findAll();
        return new RetResponse("200", "获取成功",list);
    }

    @PostMapping(path = "/findByName")
    public RetResponse<SystemParameter> find(String name){
        SystemParameter sys = systemParameterService.find(name);
        if(sys==null)
            return  new RetResponse("2001", "获取失败，未找到name对应的系统参数");
        return new RetResponse("200", "获取成功",sys);
    }
}

