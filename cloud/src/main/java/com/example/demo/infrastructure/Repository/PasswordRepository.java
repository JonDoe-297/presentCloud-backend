package com.example.demo.infrastructure.Repository;

import com.example.demo.infrastructure.DO.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PasswordRepository extends JpaRepository<Account, Integer> {
    @Query(value = "select * from user_password where login_username = ?1", nativeQuery = true)
    Account getUserPasswordByLogin_username(String username);
}
