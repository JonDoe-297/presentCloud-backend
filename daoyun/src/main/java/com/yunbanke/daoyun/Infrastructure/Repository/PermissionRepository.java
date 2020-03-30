package com.yunbanke.daoyun.Infrastructure.Repository;

import com.yunbanke.daoyun.Infrastructure.Entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
