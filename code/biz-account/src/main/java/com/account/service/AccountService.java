package com.account.service;

import com.account.domain.entity.Account;

public interface AccountService {

    public Account createAccount(Account newAccount) throws IllegalArgumentException;

    public Account updateAccount(Account updatedInfo) throws IllegalArgumentException;

    public boolean disableAccount(Long code) throws IllegalArgumentException;

    public boolean isExisted(Long code);
}
