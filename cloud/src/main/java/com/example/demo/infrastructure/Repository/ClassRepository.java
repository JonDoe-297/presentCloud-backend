package com.example.demo.infrastructure.Repository;

import com.example.demo.infrastructure.DO.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {

}
