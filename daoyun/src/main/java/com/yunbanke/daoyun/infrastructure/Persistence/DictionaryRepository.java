package com.yunbanke.daoyun.infrastructure.Persistence;


import com.yunbanke.daoyun.infrastructure.entity.Dictionary;
import org.springframework.data.repository.CrudRepository;

public interface DictionaryRepository extends CrudRepository<Dictionary, Integer> {
}
