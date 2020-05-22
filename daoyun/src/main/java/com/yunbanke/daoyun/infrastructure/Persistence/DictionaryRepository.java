package com.yunbanke.daoyun.infrastructure.Persistence;


import com.yunbanke.daoyun.infrastructure.entity.Dictionary;
import com.yunbanke.daoyun.infrastructure.entity.DictionaryDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DictionaryRepository extends CrudRepository<Dictionary, Integer> {
    List<Dictionary> findAll();
    Dictionary findDictionaryByDicId(Integer id);
    Dictionary findDictionaryByDicCode(String code);
}
