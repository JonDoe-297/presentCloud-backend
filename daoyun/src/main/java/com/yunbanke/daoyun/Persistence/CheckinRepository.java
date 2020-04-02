package com.yunbanke.daoyun.Persistence;

import com.yunbanke.daoyun.Domain.entity.Checkin;
import org.springframework.data.repository.CrudRepository;

public interface CheckinRepository extends CrudRepository<Checkin, Integer> {
}
