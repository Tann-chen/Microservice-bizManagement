package com.account.repository;

import com.account.domain.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    public Optional<Account> findById(Long id);

    public Account findByCodeAndIsAvailableTrue(Long code);

    public Account findByNameAndIsAvailableTrue(String name);

    public List<Account> findByIsAvailableTrue();
}
