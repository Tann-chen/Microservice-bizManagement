package com.account.domain.enums;


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

    public static String[] getNamesOfAccountTypes(){
        AccountType[] accountTypes = AccountType.values();
        String[] names = new String[accountTypes.length];
        for (int i = 0; i < accountTypes.length; i++) {
            names[i] = accountTypes[i].name;
        }

        return names;
    }

    public String getName() {
        return name;
    }
}
