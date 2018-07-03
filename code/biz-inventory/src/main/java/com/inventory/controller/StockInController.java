package com.inventory.controller;

import com.inventory.comm.exception.JsonParseException;
import com.inventory.comm.result.Result;
import com.inventory.comm.result.ResultBuilder;
import com.inventory.domain.entity.StockIn;
import com.inventory.service.StockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/stock_in")
public class StockInController {

    @Autowired
    private StockInService stockInService;

    @RequestMapping(value = "/{stockInId}", method = RequestMethod.GET)
    public Result getStockInDetails(@PathVariable Long stockInId) throws Exception{
        if (null == stockInId) {
            throw  new JsonParseException("stockin ID");
        }
        StockIn stockInDetails = stockInService.getStockInById(stockInId);

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(stockInDetails)
                .build();   //return details of stock_in
    }

    @RequestMapping(value = "batch/{batchNo}", method = RequestMethod.GET)
    public Result getStockInDetailsByBatchNo(@PathVariable Long batchNo) throws Exception{
        if (null == batchNo) {
            throw  new JsonParseException("batch No");
        }
        StockIn stockInDetails = stockInService.getStockInByBatchNo(batchNo);

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(stockInDetails)
                .build();   //return the stock details of the batch
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result getStockInRecords(@RequestParam(value = "accept", defaultValue = "list") String acceptType,
                                    @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception{
        Pageable pageable = new PageRequest(pageNo, pageSize);




        if (acceptType.equals("page")) {
            //return paging type list


        } else {
            //return list

        }

        return null;
    }

    @RequestMapping(value = "/entry_time", method = RequestMethod.GET)
    public Result getStockInByPeriod(@RequestParam(value = "accept", defaultValue = "list") String acceptType, @RequestParam(value = "from")Timestamp fromTime, @RequestParam(value = "to")Timestamp toTime) {
        //todo
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
