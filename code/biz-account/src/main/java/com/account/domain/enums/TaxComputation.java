package com.account.domain.enums;

import java.util.HashMap;
import java.util.Map;

public enum TaxComputation {
    group_of_taxes("Group of Taxes"),
    fixed("Fixed"),
    percentage_of_price("Percentage of Price"),
    percentage_of_price_tax_included("Percentage of Price Tax Included");

    private String name;

    TaxComputation(String name) {
        this.name = name;
    }

    public static  Map<String, String> getNameOfTaxComputation(){
        TaxComputation[] taxComputations = TaxComputation.values();
        Map<String, String>  taxComputationPairs = new HashMap<>();
        for (TaxComputation taxComputation : taxComputations) {
            taxComputationPairs.put(taxComputation.toString(), taxComputation.getName());
        }

        return taxComputationPairs;
    }

    public String getName() {
        return name;
    }
}
