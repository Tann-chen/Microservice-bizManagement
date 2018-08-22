package com.account.service.impl;

import com.account.domain.entity.Tax;
import com.account.domain.enums.TaxComputation;
import com.account.domain.enums.TaxScope;
import com.account.repository.TaxRepository;
import com.account.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaxServiceImpl implements TaxService{

    @Autowired
    private TaxRepository taxRepository;

    @Override
    public Tax createTax(Tax newTax) throws IllegalArgumentException {
        Assert.hasLength(newTax.getTaxName(), "tax name not null");

        newTax.setId(null);
        if(isTaxExisted(newTax.getTaxName())) {
            throw new IllegalArgumentException("name of tax has already existed");
        }

        return taxRepository.save(newTax);
    }

    @Override
    public Tax updateTax(Tax updatedTax) throws IllegalArgumentException {

        Long id = updatedTax.getId();
        Assert.notNull(id, "id of tax not null");
        Optional<Tax> optTax = taxRepository.findById(id);
        Tax tax = optTax.orElseThrow(()-> new IllegalArgumentException("tax is not existed"));

        if (null != updatedTax.getTaxName() && !updatedTax.getTaxName().equals(tax.getTaxName())) {
            if (isTaxExisted(updatedTax.getTaxName())) {
                throw new IllegalArgumentException("name of tax existed");
            } else {
                tax.setTaxName(updatedTax.getTaxName());
            }
        }
        if (null != updatedTax.getTaxScope()) {
            tax.setTaxScope(updatedTax.getTaxScope());
        }
        if (null != updatedTax.getTaxComputation()) {
            tax.setTaxComputation(updatedTax.getTaxComputation());
        }
        if (null != updatedTax.getAmount()) {
            tax.setAmount(updatedTax.getAmount());
        }
        if (null != updatedTax.getTaxAccount()) {
            tax.setTaxAccount(updatedTax.getTaxAccount());
        }
        if (null != updatedTax.getTaxAccountOnCreditNotes()) {
            tax.setTaxAccountOnCreditNotes(updatedTax.getTaxAccountOnCreditNotes());
        }
        if (null != updatedTax.getLabelOnInvoices()) {
            tax.setLabelOnInvoices(updatedTax.getLabelOnInvoices());
        }

        return taxRepository.save(tax);
    }

    @Override
    public boolean deActiveTax(Long id) throws IllegalArgumentException {
        Optional<Tax> optTax = taxRepository.findById(id);
        Tax tax = optTax.orElseThrow(()-> new IllegalArgumentException("tax is not existed"));

        tax.setIsActive(false);
        taxRepository.save(tax);
        return taxRepository.save(tax) == null? false : true;
    }

    @Override
    public boolean deleteTax(Long id) throws IllegalArgumentException {
        Optional<Tax> optTax = taxRepository.findById(id);
        Tax tax = optTax.orElseThrow(()-> new IllegalArgumentException("tax is not existed"));

        tax.setIsAvailable(false);
        taxRepository.save(tax);
        return taxRepository.save(tax) == null? false : true;
    }

    @Override
    public boolean isTaxExisted(String name) throws IllegalArgumentException {
        Assert.notNull(name, "name not null");
        return taxRepository.findTaxByTaxNameAndIsAvailableTrue(name) == null ? false : true;
    }

    @Override
    public List<Tax> getAllTaxes() {
        return taxRepository.findTaxesByIsAvailableTrue();
    }

    @Override
    public Tax getTaxInfo(Long id) {
        Optional<Tax> optTax = taxRepository.findById(id);
        return optTax.orElseThrow(()-> new IllegalArgumentException("tax is not existed"));
    }

    @Override
    public Map<String, String> getTaxComputationOptions() {
        return TaxComputation.getNameOfTaxComputation();
    }

    @Override
    public Map<String, String> getTaxScopeOptions() {
        return TaxScope.getNamesOfTaxScope();
    }
}
