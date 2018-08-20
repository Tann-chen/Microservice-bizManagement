package com.account.service;

import com.account.domain.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    public Account createAccount(Account newAccount) throws IllegalArgumentException;

    public Account updateAccount(Account updatedInfo) throws IllegalArgumentException;

    public boolean disableAccount(Long id) throws IllegalArgumentException;

    public boolean isExistedOfCode(Long code);

    public boolean isExistedOfName(String name);

    public Optional<Account> getAccountById(Long id);

    public List<Account> getAllAccounts();
}
