package com.inventory.controller;

import com.inventory.comm.result.Result;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/stock")
public class CurrentStockController {
    @RequestMapping(value = "/commodity/{commodityId}/items", method = RequestMethod.GET)
    public Result getStockItemsByCommodity(@PathVariable Long commodityId){
        return null;  //return list of commodities
    }

    @RequestMapping(value = "/stock_in/{stockInId}", method = RequestMethod.GET)
    public Result getStockItemsByStockInId(@PathVariable Long stockInId){
        return null; //return list
    }

    @RequestMapping(value = "/batch/{batchNo}", method = RequestMethod.GET)
    public Result getStockItemsByBatch(@PathVariable Long batchNo){
        return null; //return list
    }

    @RequestMapping(value = "/snapshot", method = RequestMethod.GET)
    public Result getStockItemsHistorySnapshot(@RequestParam("timestamp")Timestamp time){
        if (null == time) {
            //current time
        }

        // return list of items group by commodity
        return null;
    }
}
