package com.account.service;

import com.account.domain.entity.Tax;
import com.account.domain.enums.TaxComputation;
import com.account.domain.enums.TaxScope;

import java.util.List;
import java.util.Map;

public interface TaxService {

    Tax createTax(Tax newTax) throws IllegalArgumentException;

    Tax updateTax(Tax updatedTax) throws IllegalArgumentException;

    boolean deActiveTax(Long id) throws IllegalArgumentException;

    boolean deleteTax(Long id) throws IllegalArgumentException;

    boolean isTaxExisted(String name) throws IllegalArgumentException;

    List<Tax> getAllTaxes();

    Tax getTaxInfo(Long id) throws IllegalArgumentException;

    Map<String, String> getTaxComputationOptions();

    Map<String, String> getTaxScopeOptions();

}
