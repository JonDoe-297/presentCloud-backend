package com.yunbanke.daoyun.Infrastructure.Repository;

import com.yunbanke.daoyun.Infrastructure.Entity.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckinRepository extends JpaRepository<Checkin, Long> {
}
