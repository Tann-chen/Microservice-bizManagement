package com.inventory.controller;

import com.inventory.comm.result.Result;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/stock_in")
public class StockInController {
    @RequestMapping(value = "/{stockInId}", method = RequestMethod.GET)
    public Result getStockInDetails(@PathVariable Long stockInId) {
        return null;   //return details of stock_in
    }



    @RequestMapping(value = "batch/{batchNo}", method = RequestMethod.GET)
    public Result getStockInDetailsByBatchNo(){
        return null;   //return tha stock details of the batch
    }




    @RequestMapping(method = RequestMethod.GET)
    public Result getStockInRecords(@RequestParam(value = "accept", defaultValue = "list") String acceptType) {
        if (acceptType.equals("page")) {
            //return paging type list
        } else {
            //return list
        }

        return null;
    }

    @RequestMapping(value = "/entry_time", method = RequestMethod.GET)
    public Result getStockInByPeriod(@RequestParam(value = "accept", defaultValue = "list") String acceptType, @RequestParam(value = "from")Timestamp fromTime, @RequestParam(value = "to")Timestamp toTime) {
        if(null == fromTime){
            SimpleDateFormat sdf = new SimpleDateFormat("2000-01-01 00:00:00");
            String time = sdf.format(new Date());
            fromTime = Timestamp.valueOf(time);
        }

        if (null == toTime) {
            toTime = null;   //current timestamp
        }

        if (acceptType.equals("page")) {
            //return paging type list
        } else {
            //return list
        }

        return null;
    }

    @RequestMapping(value = "/commodity/{CommodityId}", method = RequestMethod.GET)
    public Result getStockInByCommodity(@PathVariable Long CommodityId, @RequestParam(value = "accept", defaultValue = "list") String acceptType) {
        if (acceptType.equals("page")) {
            //return paging
        } else {
            //return list
        }

        return null;   // return all stock_in records about one commodity
    }


    @RequestMapping(value = "/receiver/{receiverId}")
    public Result getStockInByReceiver(@PathVariable Long receiverId, @RequestParam(value = "accept", defaultValue = "list") String acceptType) {

        if (acceptType.equals("page")) {
            //return paging
        } else {
            //return list
        }

        return null;   // return all stock_in records about one the receiver
    }

}
