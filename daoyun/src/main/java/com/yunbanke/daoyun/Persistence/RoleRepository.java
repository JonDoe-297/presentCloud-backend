package com.yunbanke.daoyun.Persistence;

import com.yunbanke.daoyun.Domain.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
