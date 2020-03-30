package com.yunbanke.daoyun.Infrastructure.Repository;


import com.yunbanke.daoyun.Infrastructure.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
//Jpa 中有许多内部定义好的简单操作，可以直接使用。 其他需要的Crud可以通过@Query自己定义
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from user where user_account = ?1", nativeQuery = true)
    User getUserByUser_account(String user_account);
    @Query(value = "select * from user where is_activate = 1", nativeQuery = true)
    List<User> getActivateUsers();

}
