package com.yunbanke.daoyun.Persistence;

import com.yunbanke.daoyun.Domain.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {
}
