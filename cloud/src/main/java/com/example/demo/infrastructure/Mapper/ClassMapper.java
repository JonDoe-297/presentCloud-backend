package com.example.demo.infrastructure.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.infrastructure.DO.Class;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassMapper extends BaseMapper<Class> {

    public int createClass(Class c);
}
