package com.yunbanke.daoyun.Infrastructure.Repository;

import com.yunbanke.daoyun.Infrastructure.Entity.Permission;
import com.yunbanke.daoyun.Infrastructure.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
