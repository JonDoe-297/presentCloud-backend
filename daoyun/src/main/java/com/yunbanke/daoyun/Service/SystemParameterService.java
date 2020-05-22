package com.yunbanke.daoyun.Service;

import com.yunbanke.daoyun.infrastructure.Persistence.DictionaryDetailRepository;
import com.yunbanke.daoyun.infrastructure.Persistence.DictionaryRepository;
import com.yunbanke.daoyun.infrastructure.Persistence.SystemParameterRepository;
import com.yunbanke.daoyun.infrastructure.entity.Dictionary;
import com.yunbanke.daoyun.infrastructure.entity.DictionaryDetail;
import com.yunbanke.daoyun.infrastructure.entity.SystemParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemParameterService {
    @Autowired
    private SystemParameterRepository systemParameterRepository;

    // 新增系统参数
    public int add(String name,String parameter){
        if(systemParameterRepository.findSystemParameterBySysName(name)!=null)
            return -1;//name系统参数存在
        SystemParameter sys = new SystemParameter();
        sys.setSysName(name);
        sys.setSysParameter(parameter);
        systemParameterRepository.save(sys);
        return 1;
    }

    //删除系统参数
    public int del(int sys_id){
        if(systemParameterRepository.findSystemParameterBySysId(sys_id)==null)
            return -1;//id不存在
        systemParameterRepository.deleteById(sys_id);
        return 1;
    }

    //编辑系统参数
    public int edit(int sys_id,String name, String parameter){
        if(systemParameterRepository.findSystemParameterBySysId(sys_id)==null)
            return -1;//id不存在
        SystemParameter sys = new SystemParameter();
        sys.setSysId(sys_id);
        sys.setSysName(name);
        sys.setSysParameter(parameter);
        systemParameterRepository.save(sys);//存在id为更新，而不是新增
        return 1;
    }

    //查询系统参数列表
    public List<SystemParameter> findAll(){
        List<SystemParameter> list =systemParameterRepository.findAll();
        return list;
    }

    //查询系统参数
    public SystemParameter find(String name){
        SystemParameter sys =systemParameterRepository.findSystemParameterBySysName(name);
        return sys;
    }
 }
