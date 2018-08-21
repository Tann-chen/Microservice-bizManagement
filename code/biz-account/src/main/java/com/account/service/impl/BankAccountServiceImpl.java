package com.account.service.impl;

import com.account.domain.entity.BankAccount;
import com.account.domain.enums.Method;
import com.account.repository.BankAccountRepository;
import com.account.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public BankAccount createBankAccount(BankAccount bkAccount) throws IllegalArgumentException {
        bkAccount.setId(null);

        if (null == bkAccount.getName()) {
            throw new IllegalArgumentException("name of bank account can not be null");
        } else if (isExistedAccountName(bkAccount.getName())) {
            throw new IllegalArgumentException("this name of bank account has already existed");
        }

        if (null == bkAccount.getAccountNumber()) {
            throw new IllegalArgumentException("account number can no be null");
        } else if (isExistedAccountNumber(bkAccount.getAccountNumber())) {
            throw new IllegalArgumentException("this account number has already existed");
        }

        if (null == bkAccount.getDebitMethod()) {
            bkAccount.setDebitMethod(Method.manual);
        }

        if (null == bkAccount.getPaymentMethod()) {
            bkAccount.setPaymentMethod(Method.manual);
        }

        return bankAccountRepository.save(bkAccount);
    }


    @Override
    public BankAccount updateBankAccount(BankAccount newBkAccountInfo, Long targetAccountId) throws IllegalArgumentException {
        Optional<BankAccount> optTargetAccount = bankAccountRepository.findByIdAndIsAvailableTrue(targetAccountId);
        BankAccount targetAccount = optTargetAccount.orElseThrow(() -> new IllegalArgumentException("this bank account does not existed"));
        if (null != newBkAccountInfo.getName() && !targetAccount.getName().equals(newBkAccountInfo.getName())) {
            if (isExistedAccountName(newBkAccountInfo.getName())) {
                throw new IllegalArgumentException("name of account has already existed");
            }
            targetAccount.setName(newBkAccountInfo.getName());
        }

        if (null != newBkAccountInfo.getAccountNumber() && !targetAccount.getAccountNumber().equals(newBkAccountInfo.getAccountNumber())) {
            if (isExistedAccountNumber(newBkAccountInfo.getAccountNumber())) {
                throw new IllegalArgumentException("account number has already existed");
            }
            targetAccount.setAccountNumber(newBkAccountInfo.getAccountNumber());
        }

        if (null != newBkAccountInfo.getBank()) {
            targetAccount.setBank(newBkAccountInfo.getBank());
        }

        if (null != newBkAccountInfo.getDebitMethod()) {
            targetAccount.setDebitMethod(newBkAccountInfo.getDebitMethod());
        }

        if (null != newBkAccountInfo.getPaymentMethod()) {
            targetAccount.setPaymentMethod(newBkAccountInfo.getPaymentMethod());
        }

        return bankAccountRepository.save(targetAccount);
    }


    @Override
    public void disableBankAccount(Long targetAccountId) throws IllegalArgumentException {
        Optional<BankAccount> optTargetAccount = bankAccountRepository.findByIdAndIsAvailableTrue(targetAccountId);
        BankAccount targetAccount = optTargetAccount.orElseThrow(() -> new IllegalArgumentException("the bank account does not existed"));
        targetAccount.setIsAvailable(false);
        bankAccountRepository.save(targetAccount);
    }


    @Override
    public Optional<BankAccount> getBankAccountInfo(Long targetAccountId) {
        return bankAccountRepository.findByIdAndIsAvailableTrue(targetAccountId);
    }


    @Override
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findByIsAvailableTrue();
    }


    @Override
    public boolean isExistedAccountNumber(String accountNumber) {
        Optional<BankAccount> optAccount = bankAccountRepository.findByAccountNumberAndIsAvailableTrue(accountNumber);
        return optAccount.isPresent();
    }


    @Override
    public boolean isExistedAccountName(String name) {
        Optional<BankAccount> optAccount = bankAccountRepository.findByNameAndIsAvailableTrue(name);
        return optAccount.isPresent();
    }

}
