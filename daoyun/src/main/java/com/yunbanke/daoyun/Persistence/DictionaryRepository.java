package com.yunbanke.daoyun.Persistence;

import com.yunbanke.daoyun.Domain.entity.Dictionary;
import org.springframework.data.repository.CrudRepository;

public interface DictionaryRepository extends CrudRepository<Dictionary, Integer> {
}
