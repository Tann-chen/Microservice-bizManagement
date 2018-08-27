package com.account.controller;

import com.account.comm.exception.JsonParseException;
import com.account.comm.result.Result;
import com.account.domain.entity.Tax;
import com.account.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("taxes/tax")
public class TaxController {

    @Autowired
    private TaxService taxService;

    @PostMapping
    public Result createTax(@RequestBody Tax tax) throws Exception {
        if (null == tax) {
            throw new JsonParseException("tax");
        }

        Tax createdTax = taxService.createTax(tax);
        List<Tax> taxList = taxService.getAllTaxes();

        return new Result.Builder()
                .setCode(Result.Builder.SUCCESS)
                .setData(taxList)
                .build();
    }

    @PutMapping("/{id}")
    public Result updateTax(@PathVariable Long id, @RequestBody Tax tax) throws Exception {
        if (null == id) {
            throw new JsonParseException("taxId");
        }
        if (null == tax) {
            throw new JsonParseException("tax");
        }

        tax.setId(id);

        Tax updatedTax = taxService.updateTax(tax);
        List<Tax> taxList = taxService.getAllTaxes();

        return new Result.Builder()
                .setCode(Result.Builder.SUCCESS)
                .setData(taxList)
                .build();
    }

    @DeleteMapping("/{id}")
    public Result deleteTax(@PathVariable Long id) throws Exception {
        if (null == id) {
            throw new JsonParseException("taxId");
        }
        taxService.deleteTax(id);
        List<Tax> taxList = taxService.getAllTaxes();

        return new Result.Builder()
                .setCode(Result.Builder.SUCCESS)
                .setData(taxList)
                .build();
    }

    @PutMapping("/de_active/{id}")
    public Result deActiveTax(@PathVariable Long id) throws Exception {
        if (null == id) {
            throw new JsonParseException("taxId");
        }
        taxService.deActiveTax(id);
        List<Tax> taxList = taxService.getAllTaxes();

        return new Result.Builder()
                .setCode(Result.Builder.SUCCESS)
                .setData(taxList)
                .build();
    }

    @GetMapping
    public Result getAllTaxes() {
        List<Tax> taxList = taxService.getAllTaxes();

        return new Result.Builder()
                .setCode(Result.Builder.SUCCESS)
                .setData(taxList)
                .build();
    }

    @GetMapping("/{id}")
    public Result getTaxInfo(@PathVariable Long id) {
        if (null == id) {
            throw new JsonParseException("taxId");
        }
        Tax tax = taxService.getTaxInfo(id);

        return new Result.Builder()
                .setCode(Result.Builder.SUCCESS)
                .setData(tax)
                .build();
    }

    @RequestMapping(value = "/tax_comput/taxation_options", method = RequestMethod.OPTIONS)
    public Result getTaxComputationOptions() {
        Map<String, String> taxComputationPairs = taxService.getTaxComputationOptions();
        return new Result.Builder()
                .setCode(Result.Builder.SUCCESS)
                .setData(taxComputationPairs)
                .build();
    }

    @RequestMapping(value = "/tax_scope_options", method = RequestMethod.OPTIONS)
    public Result getTaxScopeOptions() {
        Map<String, String> taxScopePairs = taxService.getTaxComputationOptions();
        return new Result.Builder()
                .setCode(Result.Builder.SUCCESS)
                .setData(taxScopePairs)
                .build();
    }
}
