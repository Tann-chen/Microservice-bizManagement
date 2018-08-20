package com.account.repository;

import com.account.domain.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends CrudRepository<Account, Long>{
    public Account findByCodeAndIsAvailableTrue();


}
