package com.yunbanke.daoyun.Infrastructure.Repository;

import com.yunbanke.daoyun.Infrastructure.Entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
