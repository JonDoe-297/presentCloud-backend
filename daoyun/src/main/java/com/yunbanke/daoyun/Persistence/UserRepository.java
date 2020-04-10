package com.yunbanke.daoyun.Persistence;

import com.yunbanke.daoyun.Domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface UserRepository extends CrudRepository<User, Integer> {
}
