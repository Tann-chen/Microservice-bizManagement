package com.account.repository;

import com.account.domain.entity.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, Long>{
    Optional<BankAccount> findByIdAndIsAvailableTrue(Long id);

    List<BankAccount> findByIsAvailableTrue();

    Optional<BankAccount> findByNameAndIsAvailableTrue(String name);

    Optional<BankAccount> findByAccountNumberAndIsAvailableTrue(String accountNumber);
}
