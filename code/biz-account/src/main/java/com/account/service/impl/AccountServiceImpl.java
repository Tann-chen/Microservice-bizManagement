package com.account.service.impl;


import com.account.domain.entity.Account;
import com.account.repository.AccountRepository;
import com.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account newAccount) throws IllegalArgumentException {
        if(isExistedOfCode(newAccount.getCode())){
            throw new IllegalArgumentException("code of account has already existed");
        }

        if(isExistedOfName(newAccount.getName())){
            throw new IllegalArgumentException("name of account has already existed");
        }

        if(newAccount.getAccountType() == null){
            throw new IllegalArgumentException("type of account cannot be null");
        }

        return accountRepository.save(newAccount);
    }


    @Override
    public Account updateAccount(Account updatedInfo) throws IllegalArgumentException {
        Long id = updatedInfo.getId();

        if(id == null){
            throw new IllegalArgumentException("id of account inputted cannot be null");
        }
        Optional<Account> optAccount = accountRepository.findById(id);

        Account account = optAccount.orElseThrow(()-> new IllegalArgumentException("the account does not existed"));


        if(null != updatedInfo.getCode() && !updatedInfo.getCode().equals(account.getCode())){
            if(isExistedOfCode(updatedInfo.getCode())){
                throw new IllegalArgumentException("code of account has already existed");
            }else{
                account.setCode(updatedInfo.getCode());
            }
        }

        if(null != updatedInfo.getName() && !updatedInfo.getName().equals(account.getName())){
            if(isExistedOfName(updatedInfo.getName())){
                throw new IllegalArgumentException("name of account has already existed");
            }else{
                account.setName(updatedInfo.getName());
            }
        }

        if(updatedInfo.getAccountType() != null){
            account.setAccountType(updatedInfo.getAccountType());
        }
        if(updatedInfo.getAllowReconciliation() != null){
            account.setAllowReconciliation(updatedInfo.getAllowReconciliation());
        }

        if(updatedInfo.getIsDeprecated() != null){
            account.setIsDeprecated(updatedInfo.getIsDeprecated());
        }

        if(updatedInfo.getIsAvailable() != null){
            account.setIsAvailable(updatedInfo.getIsAvailable());
        }

        return accountRepository.save(account);
    }


    @Override
    public boolean disableAccount(Long id) throws IllegalArgumentException {
        Optional<Account> optAccount = accountRepository.findById(id);
        Account account = optAccount.orElseThrow(()-> new IllegalArgumentException("the account does not existed"));

        account.setIsAvailable(false);
        accountRepository.save(account);
        return true;
    }


    @Override
    public boolean isExistedOfCode(Long code) {
        Account account = accountRepository.findByCodeAndIsAvailableTrue(code);
        return account != null;
    }


    @Override
    public boolean isExistedOfName(String name) {
        Account account = accountRepository.findByNameAndIsAvailableTrue(name);
        return account != null;
    }


    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }


    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findByIsAvailableTrue();
    }
}
