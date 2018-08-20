package com.account.service.impl;


import com.account.domain.entity.Account;
import com.account.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    @Override
    public Account createAccount(Account newAccount) throws IllegalArgumentException {

    }

    @Override
    public Account updateAccount(Account updatedInfo) throws IllegalArgumentException {
        return null;
    }

    @Override
    public boolean disableAccount(Long code) throws IllegalArgumentException {
        return false;
    }

    @Override
    public boolean isExisted(Long code) {
        return false;
    }
}
