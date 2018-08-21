package com.account.controller;

import com.account.comm.exception.JsonParseException;
import com.account.comm.result.Result;
import com.account.domain.entity.Tax;
import com.account.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tax")
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
        
        return null;
    }

    @PutMapping("/deActive/{id}")
    public Result deActiveTax(@PathVariable Long id) throws Exception {
        return null;
    }

    @GetMapping
    public Result getAllTaxes() {
        return null;
    }

    @GetMapping("/{id}")
    public Result getTaxInfo() {
        return null;
    }

    @RequestMapping(value = "/taxComputationOptions", method = RequestMethod.OPTIONS)
    public Result getTaxComputationOptions() {
        return null;
    }

    @RequestMapping(value = "/taxScopeOptions", method = RequestMethod.OPTIONS)
    public Result getTaxScopeOptions() {
        return null;
    }
}
