package com.account.domain.enums;

public enum JournalType {
    sale("sale"),
    purchase("purchase"),
    cash("cash"),
    bank("bank"),
    miscellaneous("miscellaneous");


    private String name;

    JournalType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
