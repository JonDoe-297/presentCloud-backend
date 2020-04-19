package com.example.demo.infrastructure.Repository;

import com.example.demo.infrastructure.DO.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findById(int id);
    List<Role> findRolesById(int id);
}
