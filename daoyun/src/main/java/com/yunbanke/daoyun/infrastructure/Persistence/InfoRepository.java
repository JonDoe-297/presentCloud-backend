package com.yunbanke.daoyun.infrastructure.Persistence;

import com.yunbanke.daoyun.infrastructure.entity.Info;
import org.springframework.data.repository.CrudRepository;

public interface InfoRepository extends CrudRepository<Info, Integer> {
}
