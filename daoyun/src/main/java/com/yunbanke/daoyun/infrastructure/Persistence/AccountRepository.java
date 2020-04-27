package com.yunbanke.daoyun.infrastructure.Persistence;

import com.yunbanke.daoyun.infrastructure.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findAccountsByLoginphoneAndLoginpasswd(String login_phone, String login_passwd);

    List<Account> findAccountsByLoginemailAndLoginpasswd(String login_email, String login_passwd);

    List<Account> findAccountsByLoginaccountAndLoginpasswd(String login_account, String login_passwd);

    Account findAccountByLoginphone(String phone);

    Account findAccountByLoginemail(String email);

    Account findAccountByLoginaccount(String account);
}
