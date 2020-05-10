package com.yunbanke.daoyun.infrastructure.Persistence;

import com.yunbanke.daoyun.infrastructure.entity.Checkin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CheckinRepository extends CrudRepository<Checkin, Integer> {
    Checkin getCheckinByClassnumAndCheckininfoidAndUserid(String classnum, Integer checkininfoid, Integer userid);
    List<Checkin> getCheckinsByClassnumAndCheckininfoid(String classnum, Integer checkininfoid);
}
