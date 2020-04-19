package com.example.demo.infrastructure.Repository;

import com.example.demo.infrastructure.DO.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {
}
