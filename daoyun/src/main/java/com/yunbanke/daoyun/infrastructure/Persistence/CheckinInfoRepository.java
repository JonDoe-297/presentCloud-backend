package com.yunbanke.daoyun.infrastructure.Persistence;

import com.yunbanke.daoyun.infrastructure.entity.CheckinInfo;
import org.hibernate.annotations.Check;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CheckinInfoRepository extends CrudRepository<CheckinInfo, Integer> {
    List<CheckinInfo> getCheckInInfosByClassnum(String classNum);
    List<CheckinInfo> getCheckinInfoByCheckininfoid(Integer id);
}
