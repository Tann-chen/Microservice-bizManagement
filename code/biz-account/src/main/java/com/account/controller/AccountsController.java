package com.account.controller;


import com.account.comm.exception.JsonParseException;
import com.account.comm.result.Result;
import com.account.domain.entity.Account;
import com.account.domain.enums.AccountType;
import com.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/chart_of_account")
public class AccountsController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(path = "/account", method = RequestMethod.POST)
    public Result createAccount(@RequestBody Account account) throws Exception {
        if (null == account) {
            throw new JsonParseException("account");
        }

        accountService.createAccount(account);

        //get updated account list
        List<Account> accountList = accountService.getAllAccounts();

        return new Result.Builder()
                .setCode(Result.Builder.SUCCESS)
                .setData(accountList)
                .build();
    }


    @RequestMapping(path = "/accountType", method = RequestMethod.OPTIONS)
    public Result getAccountTypes() {
        //key:enum name, value : name attribute of enum
        Map<String, String> accountTypes = AccountType.getAllAccountTypes();

        return new Result.Builder()
                .setCode(Result.Builder.SUCCESS)
                .setData(accountTypes)
                .build();
    }


    @RequestMapping(path = "/account", method = RequestMethod.GET)
    public Result getAccountsList() {
        List<Account> accountList = accountService.getAllAccounts();
        return new Result.Builder()
                .setCode(Result.Builder.SUCCESS)
                .setData(accountList)
                .build();
    }

    @RequestMapping(path = "/account/{accountId}", method = RequestMethod.GET)
    public Result getAccountInfo(@PathVariable Long accountId) {
        Optional<Account> optAccountInfo = accountService.getAccountById(accountId);
        Result.Builder resultBuilder = new Result.Builder();

        if(optAccountInfo.isPresent()){
            return resultBuilder.setCode(Result.Builder.SUCCESS).setData(optAccountInfo.get()).build();
        }else{
            return resultBuilder.setCode(Result.Builder.FAILED).setMessage("No account matching the accountId").build();
        }
    }


    @RequestMapping(path = "/account/{accountId}", method = RequestMethod.PUT)
    public Result updateAccountInfo(@RequestBody Account account, @PathVariable Long accountId) throws Exception{
        if(null == account) {
            throw new JsonParseException("account");
        }

        if(null == accountId){
            throw new JsonParseException("accountId");
        }

        //set target's id with inputted
        account.setId(accountId);

        Account newAccount = accountService.updateAccount(account);

        List<Account> currentAccountList = accountService.getAllAccounts();

        Map<String, Object> response = new HashMap<>();

        response.put("updatedAccountInfo", newAccount);
        response.put("updatedAccountList", currentAccountList);

        return new Result.Builder()
                .setCode(Result.Builder.SUCCESS)
                .setData(response)
                .build();
    }


    @RequestMapping(path = "/account/{accountId}", method = RequestMethod.DELETE)
    public Result deleteAccount(@PathVariable Long accountId) throws Exception{
        if(null == accountId){
            throw new JsonParseException("accountId");
        }

        accountService.disableAccount(accountId);

        List<Account> currentAccountList = accountService.getAllAccounts();

        return new Result.Builder()
                .setCode(Result.Builder.SUCCESS)
                .setData(currentAccountList)
                .build();
    }

}
