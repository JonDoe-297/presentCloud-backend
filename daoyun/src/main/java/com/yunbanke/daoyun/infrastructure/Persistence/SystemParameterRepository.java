package com.yunbanke.daoyun.infrastructure.Persistence;


import com.yunbanke.daoyun.infrastructure.entity.Dictionary;
import com.yunbanke.daoyun.infrastructure.entity.SystemParameter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SystemParameterRepository extends CrudRepository<SystemParameter, Integer> {
    SystemParameter findSystemParameterBySysName(String name);
    SystemParameter findSystemParameterBySysId(Integer id);
    List<SystemParameter> findAll();
}
