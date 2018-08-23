package com.account.controller;

import com.account.comm.exception.JsonParseException;
import com.account.comm.result.Result;
import com.account.domain.entity.BankAccount;
import com.account.domain.enums.Method;
import com.account.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/bank_account")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;


    @RequestMapping(path = "/account", method = RequestMethod.GET)
    public Result getAllBankAccounts() {
        List<BankAccount> bankAccountList = bankAccountService.getAllBankAccounts();
        return new Result.Builder()
                .setCode(Result.Builder.SUCCESS)
                .setData(bankAccountList)
                .build();
    }


    @RequestMapping(path = "/account/{accountId}", method = RequestMethod.GET)
    public Result getBankAccountInfo(@PathVariable Long accountId) throws Exception {
        if(null == accountId){
            throw new JsonParseException("accountId");
        }

        Optional<BankAccount> optBankAccount = bankAccountService.getBankAccountInfo(accountId);

        Result.Builder resultBuilder = new Result.Builder();
        if(optBankAccount.isPresent()){
            return resultBuilder.setCode(Result.Builder.SUCCESS).setData(optBankAccount.get()).build();
        }else{
            return resultBuilder.setCode(Result.Builder.FAILED).setMessage("the account does not existed").build();
        }
    }


    @RequestMapping(path = "/account/{accountId}", method = RequestMethod.PUT)
    public Result editBankAccountInfo(@RequestBody BankAccount newBkAccountInfo, @PathVariable Long accountId) {
        if(null == newBkAccountInfo){
            throw new JsonParseException("account");
        }

        if(null == accountId){
            throw new JsonParseException("accountId");
        }

        BankAccount upatedAccountInfo = bankAccountService.updateBankAccount(newBkAccountInfo, accountId);
        List<BankAccount> currentbankAccountList = bankAccountService.getAllBankAccounts();
        Map<String, Object> response = new HashMap<>();
        response.put("updatedBkAccount", upatedAccountInfo);
        response.put("updatedBkAccountList", currentbankAccountList);
        return new Result.Builder()
                .setCode(Result.Builder.SUCCESS)
                .setData(response)
                .build();
    }


    @RequestMapping(path = "/account/{accountId}")
    public Result deleteBankAccount(@PathVariable Long accountId) {
        if(null == accountId){
            throw new JsonParseException("accountId");
        }

        bankAccountService.disableBankAccount(accountId);

        List<BankAccount> currentBankAccountList = bankAccountService.getAllBankAccounts();

        return new Result.Builder()
                .setCode(Result.Builder.SUCCESS)
                .setData(currentBankAccountList)
                .build();
    }


    @RequestMapping(path = "/method", method = RequestMethod.OPTIONS)
    public Result getDebitPaymentMethods() {
        Map<String, String> response = new HashMap<>();
        Method[] allMethods = Method.values();
        for (Method m : allMethods){
            response.put(m.toString(), m.name());
        }

        return new Result.Builder()
                .setCode(Result.Builder.SUCCESS)
                .setData(response)
                .build();

    }
}
