package com.account.service;

import com.account.domain.entity.BankAccount;

import java.util.List;
import java.util.Optional;

public interface BankAccountService {

    public BankAccount createBankAccount(BankAccount bkAccount) throws IllegalArgumentException;

    public BankAccount updateBankAccount(BankAccount newBkAccountInfo, Long targetAccountId) throws IllegalArgumentException;

    public void disableBankAccount(Long targetAccountId) throws IllegalArgumentException;

    public Optional<BankAccount> getBankAccountInfo(Long targetAccountId);

    public List<BankAccount> getAllBankAccounts();

    public boolean isExistedAccountNumber(String accountNumber);

    public boolean isExistedAccountName(String name);
}
