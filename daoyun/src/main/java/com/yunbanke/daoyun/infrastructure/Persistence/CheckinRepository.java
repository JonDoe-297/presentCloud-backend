package com.yunbanke.daoyun.infrastructure.Persistence;

import com.yunbanke.daoyun.infrastructure.entity.Checkin;
import org.springframework.data.repository.CrudRepository;

public interface CheckinRepository extends CrudRepository<Checkin, Integer> {
}
