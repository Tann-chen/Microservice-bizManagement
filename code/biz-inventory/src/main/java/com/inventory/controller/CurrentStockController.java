package com.inventory.controller;

import com.inventory.comm.result.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class CurrentStockController {


    @RequestMapping(value = "/commodity/{commodityId}/items")
    public Result getStockItemsByCommodity(@PathVariable Long commodityId){
        return null;  //return list of commodities
    }

    @RequestMapping(value = "/commodity/{commodityId}/count")
    public Result getStockTotalCountOfItemsByCommodity(){
        return null;    //return int
    }
}
