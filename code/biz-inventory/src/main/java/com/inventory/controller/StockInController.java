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

    @RequestMapping(value = "/batch/{batchNo}", method = RequestMethod.GET)
    public Result getStockInDetailsByBatchNo(@PathVariable String batchNo) throws Exception{
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
        Object allStockIn;
        if (acceptType.equals("page")) {
            //return paging type list
            Pageable pageable = new PageRequest(pageNo, pageSize);
            allStockIn = stockInService.getAllStockIn(pageable);
        } else {
            //return list
            allStockIn = stockInService.getAllStockIn();
        }

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(allStockIn)
                .build();
    }

    @RequestMapping(value = "/entry_time", method = RequestMethod.GET)
    public Result getStockInByPeriod(@RequestParam(value = "accept", defaultValue = "list") String acceptType,
                                     @RequestParam(value = "from", required = false)Timestamp fromTime,
                                     @RequestParam(value = "to", required = false)Timestamp toTime,
                                     @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(null == fromTime){
            String preTime = "2000-01-01 00:00:00";
            fromTime = Timestamp.valueOf(preTime);
        }
        if (null == toTime) {
            String curTime = sdf.format(new Date());
            toTime = Timestamp.valueOf(curTime);   //current timestamp
        }
        Object stockInsByPeriod;
        if (acceptType.equals("page")) {
            //return paging type list
            Pageable pageable = new PageRequest(pageNo, pageSize);
            stockInsByPeriod = stockInService.getStockInByPeriod(pageable, fromTime, toTime);
        } else {
            //return list
            stockInsByPeriod = stockInService.getStockInByPeriod(fromTime, toTime);
        }

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(stockInsByPeriod)
                .build();
    }

    @RequestMapping(value = "/commodity/{commodityId}", method = RequestMethod.GET)
    public Result getStockInByCommodity(@PathVariable Long commodityId,
                                        @RequestParam(value = "accept", defaultValue = "list") String acceptType,
                                        @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception{
        if (null == commodityId) {
            throw new JsonParseException("commodity Id");
        }
        Object stockInsByCommodity;
        if (acceptType.equals("page")) {
            //return paging
            Pageable pageable = new PageRequest(pageNo, pageSize);
            stockInsByCommodity = stockInService.getStockInByCommodity(pageable, commodityId);
        } else {
            //return list
            stockInsByCommodity = stockInService.getStockInByCommodity(commodityId);
        }

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(stockInsByCommodity)
                .build();   // return all stock_in records about one commodity
    }


    @RequestMapping(value = "/receiver/{receiverId}", method = RequestMethod.GET)
    public Result getStockInByReceiver(@PathVariable Long receiverId,
                                       @RequestParam(value = "accept", defaultValue = "list") String acceptType,
                                       @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception{
        if (null == receiverId) {
            throw new JsonParseException("receiver Id");
        }
        Object stockInsByReceiver;
        if (acceptType.equals("page")) {
            //return paging
            Pageable pageable = new PageRequest(pageNo, pageSize);
            stockInsByReceiver = stockInService.getStockInByReceiver(pageable, receiverId);
        } else {
            //return list
            stockInsByReceiver = stockInService.getStockInByReceiver(receiverId);
        }

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(stockInsByReceiver)
                .build();   // return all stock_in records about one the receiver
    }

}
