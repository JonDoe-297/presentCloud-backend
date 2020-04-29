package com.yunbanke.daoyun.infrastructure.Persistence;

import com.yunbanke.daoyun.infrastructure.entity.CheckinInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CheckinInfoRepository extends CrudRepository<CheckinInfo, Integer> {
    List<CheckinInfo> getCheckInInfosByClassnum(String classNum);
}
