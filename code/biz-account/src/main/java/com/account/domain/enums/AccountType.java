package com.account.domain.enums;


import java.util.HashMap;
import java.util.Map;

public enum AccountType {
    receivable("Receivable"),
    payable("Payable"),
    bank_and_cash("Bank and Cash"),
    credit_card("Credit Card"),
    current_assets("Current Assets"),
    non_current_assets("Non-current Assets"),
    prepayments("Prepayments"),
    fixed_assets("Fixed Assets"),
    current_liabilities("Current Liabilities"),
    non_current_liabilities("Non-current Liabilities"),
    equity("Equity"),
    current_year_earnings("Current Year Earnings"),
    other_income("Other Income"),
    income("Income"),
    depreciation("Depreciation"),
    expenses("Expenses"),
    cost_of_revenue("Cost of Revenue");


    private String name;

    AccountType(String name){
        this.name = name;
    }

    public static Map<String, String> getAllAccountTypes(){
        AccountType[] accountTypes = AccountType.values();
        Map<String, String> accountTypePairs = new HashMap<>();

        for (int i = 0; i < accountTypes.length; i++) {
            accountTypePairs.put(accountTypes[i].toString(), accountTypes[i].getName());
        }

        return accountTypePairs;
    }

    public String getName() {
        return name;
    }
}
