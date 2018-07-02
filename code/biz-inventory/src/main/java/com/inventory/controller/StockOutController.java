package com.inventory.controller;


import com.inventory.comm.result.Result;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/stock_out")
public class StockOutController {

    @RequestMapping(value = "/{stockOutId}", method = RequestMethod.GET)
    public Result getStockOutDetails(@PathVariable Long stockOutId) {
        return null; //return the details of the stock out
    }

    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    public Result getStockOutDetailsByItem(@PathVariable Long itemId) {
        return null; //return the details of the stock out
        //item is the specific unit of the commodity
        //commodity is type of items
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result getStockOut(@RequestParam(value = "accept", defaultValue = "list") String acceptType) {
        if (acceptType.equals("page")) {
            //return paging type list
        } else {
            //return list
        }

        return null;    //return all stock out records
    }


    @RequestMapping(value = "/pick_time", method = RequestMethod.GET)
    public Result getStockOutByPickedTime(@RequestParam(value = "accept", defaultValue = "list") String acceptType, @RequestParam(value = "from") Timestamp fromTime, @RequestParam(value = "to") Timestamp toTime) {
        if (null == fromTime) {
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

    @RequestMapping(value = "/purpose", method = RequestMethod.GET)
    public Result getStockOutByPurpose(@RequestParam(value = "accept", defaultValue = "list") String acceptType, @RequestParam(value = "purpose") String purpose) {
        //please do switch from StockOutPurpose enums
        if (acceptType.equals("page")) {
            //return paging type list
        } else {
            //return list
        }

        return null;    //return stock out records
    }

    @RequestMapping(value = "pick_user", method = RequestMethod.GET)
    public Result getStockOutByPickedUser(@RequestParam(value = "accept", defaultValue = "list") String acceptType, @RequestParam(value = "pick_user") Long pickUser) {
        if (acceptType.equals("page")) {
            //return paging type list
        } else {
            //return list
        }

        return null;    //return stock out records
    }

    @RequestMapping(value = "approve_user", method = RequestMethod.GET)
    public Result getStockOutByApprovedUser(@RequestParam(value = "accept", defaultValue = "list") String acceptType, @RequestParam(value = "approve_user") Long ApprovedUser) {
        if (acceptType.equals("page")) {
            //return paging type list
        } else {
            //return list
        }

        return null;    //return stock out records
    }


    //this is a little challenging, try your best
    @RequestMapping(value = "/~criterion", method = RequestMethod.POST)
    public Result getStockOutByCustomCriterion(@RequestParam(value = "accept", defaultValue = "list") String acceptType, @RequestBody HashMap<String, Object> criterion){
        return null;
    }
}
