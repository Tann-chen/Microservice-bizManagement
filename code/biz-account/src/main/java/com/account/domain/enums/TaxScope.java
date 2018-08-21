package com.account.domain.enums;

import java.util.HashMap;
import java.util.Map;

public enum TaxScope {
    sales("Sales"),
    purchase("Purchase"),
    none("None");

    private String name;

    TaxScope(String name) {
        this.name = name;
    }

    public static Map<String, String> getNamesOfTaxScope(){
        TaxScope[] taxScopes = TaxScope.values();
        Map<String, String> taxScopePairs = new HashMap<>();
        for (TaxScope taxScope : taxScopes) {
            taxScopePairs.put(taxScope.toString(), taxScope.getName());
        }

        return taxScopePairs;
    }

    public String getName() {
        return name;
    }
}
