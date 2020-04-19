package com.example.demo.infrastructure.Repository;

import com.example.demo.infrastructure.DO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByEmail(String email);
    User getUserByEmail(String email);

}
