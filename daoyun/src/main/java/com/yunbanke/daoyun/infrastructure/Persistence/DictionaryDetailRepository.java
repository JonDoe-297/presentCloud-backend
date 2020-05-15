package com.yunbanke.daoyun.infrastructure.Persistence;

import com.yunbanke.daoyun.infrastructure.entity.DictionaryDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface DictionaryDetailRepository extends CrudRepository<DictionaryDetail, Integer> {
    List<DictionaryDetail> findByDicTypeId(long DicTypeId);
}
