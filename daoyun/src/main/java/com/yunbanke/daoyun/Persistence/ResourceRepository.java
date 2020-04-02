package com.yunbanke.daoyun.Persistence;

import com.yunbanke.daoyun.Domain.entity.Resource;
import org.springframework.data.repository.CrudRepository;

public interface ResourceRepository extends CrudRepository<Resource, Integer> {
}
