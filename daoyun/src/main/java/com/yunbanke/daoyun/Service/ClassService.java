package com.yunbanke.daoyun.Service;

import com.yunbanke.daoyun.infrastructure.entity.Class;
import com.yunbanke.daoyun.infrastructure.Persistence.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository;

    private int addClass(String className, Integer userId){
        Class c = new Class();
        c.setClass_name(className);
        c.setUser_id(userId);
        Random r = new Random();
        c.setClass_num(""+r.nextInt(10000000));
        return 1;
    }
}
