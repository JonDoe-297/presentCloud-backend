package com.yunbanke.daoyun.Persistence;

import com.yunbanke.daoyun.Domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    List<Account> findAccountsByLoginphoneAndLoginpasswd(String login_phone, String login_passwd);
    List<Account> findAccountsByLoginemailAndLoginpasswd(String login_email, String login_passwd);
    List<Account> findAccountsByLoginaccountAndLoginpasswd(String login_account, String login_passwd);
}
