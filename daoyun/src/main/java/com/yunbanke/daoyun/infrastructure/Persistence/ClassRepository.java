package com.yunbanke.daoyun.infrastructure.Persistence;

import com.yunbanke.daoyun.infrastructure.entity.Class;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClassRepository extends CrudRepository<Class, Integer> {
    public List<Class> getClassesByClassnum(String classNum);
    public List<Class> getClassesByClassname(String className);
    public List<Class> getClassesByUserid(Integer userId);
}
