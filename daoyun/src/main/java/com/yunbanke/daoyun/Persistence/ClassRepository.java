package com.yunbanke.daoyun.Persistence;

import com.yunbanke.daoyun.Domain.entity.Class;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClassRepository extends CrudRepository<Class, Integer> {
    public List<Class> getClassesByClassnum(String classNum);
}
