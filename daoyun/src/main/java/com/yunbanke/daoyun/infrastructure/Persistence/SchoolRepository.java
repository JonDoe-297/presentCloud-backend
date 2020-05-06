package com.yunbanke.daoyun.infrastructure.Persistence;

import com.yunbanke.daoyun.infrastructure.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SchoolRepository extends JpaRepository<School, Integer> {
    @Query(value = "select * from school where school_name = ?1", nativeQuery = true)
    School findSchoolByschool_name(String school_name);
}
