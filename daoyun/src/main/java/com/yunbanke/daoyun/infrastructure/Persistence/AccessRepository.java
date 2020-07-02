package com.yunbanke.daoyun.infrastructure.Persistence;

import com.yunbanke.daoyun.infrastructure.entity.Access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AccessRepository extends JpaRepository<Access, Integer> {
    @Query(value = "select * from access where access_name = ?1", nativeQuery = true)
    Access findAccessByAccess_name(String name);
}
