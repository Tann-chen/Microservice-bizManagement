package com.inventory.controller;


import com.inventory.comm.exception.JsonParseException;
import com.inventory.comm.result.Result;
import com.inventory.comm.result.ResultBuilder;
import com.inventory.domain.entity.StockOut;
import com.inventory.domain.enums.StockOutPurpose;
import com.inventory.service.StockOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/stock_out")
public class StockOutController {

    @Autowired
    private StockOutService stockOutService;

    @RequestMapping(value = "/{stockOutId}", method = RequestMethod.GET)
    public Result getStockOutDetails(@PathVariable Long stockOutId) throws Exception {
        if (null == stockOutId) {
            throw new JsonParseException("stockOut Id");
        }
        StockOut stockOutDetail = stockOutService.getStockOutDetail(stockOutId);

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(stockOutDetail)
                .build(); //return the details of the stock out

    }

    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    public Result getStockOutDetailsByItem(@PathVariable Long itemId) throws Exception {
        if (null == itemId) {
            throw new JsonParseException("itemId");
        }
        StockOut stockOutDetail = stockOutService.getStockOutDetailByItem(itemId);

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(stockOutDetail)
                .build();
        //return the details of the stock out
        //item is the specific unit of the commodity
        //commodity is type of items
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result getStockOut(@RequestParam(value = "accept", defaultValue = "list") String acceptType,
                              @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        Object allStockOut;
        if (acceptType.equals("page")) {
            //return paging type list
            Pageable pageable = new PageRequest(pageNo, pageSize);
            allStockOut = stockOutService.getAllStockOut(pageable);
        } else {
            //return list
            allStockOut = stockOutService.getAllStockOut();
        }

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(allStockOut)
                .build();    //return all stock out records
    }

    @RequestMapping(value = "/pick_time", method = RequestMethod.GET)
    public Result getStockOutByPickedTime(@RequestParam(value = "accept", defaultValue = "list") String acceptType,
                                          @RequestParam(value = "from") Timestamp fromTime,
                                          @RequestParam(value = "to") Timestamp toTime,
                                          @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(null == fromTime){
            String preTime = "2000-01-01 00:00:00";
            fromTime = Timestamp.valueOf(preTime);
        }
        if (null == toTime) {
            String curTime = sdf.format(new Date());
            toTime = Timestamp.valueOf(curTime);   //current timestamp
        }
        Object stockOutByPickedTime;
        if (acceptType.equals("page")) {
            //return paging type list
            Pageable pageable = new PageRequest(pageNo, pageSize);
            stockOutByPickedTime = stockOutService.getStockOutByPickedTime(pageable, fromTime, toTime);
        } else {
            //return list
            stockOutByPickedTime = stockOutService.getStockOutByPickedTime(fromTime, toTime);
        }

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(stockOutByPickedTime)
                .build();
    }

    @RequestMapping(value = "/purpose", method = RequestMethod.GET)
    public Result getStockOutByPurpose(@RequestParam(value = "accept", defaultValue = "list") String acceptType,
                                       @RequestParam(value = "purpose") String purpose,
                                       @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        if (null == purpose) {
            throw new JsonParseException("purpose");
        }
        //please do switch from StockOutPurpose enums
        StockOutPurpose purposeEnum = StockOutPurpose.getStockOutPurposeByString(purpose);
        if (null == purposeEnum) {
            throw new JsonParseException("purpose do not match");
        }
        Object stockOutByPurpose;
        if (acceptType.equals("page")) {
            Pageable pageable = new PageRequest(pageNo, pageSize);
            stockOutByPurpose = stockOutService.getStockOutByPurpose(pageable, purposeEnum);
            //return paging type list
        } else {
            //return list
            stockOutByPurpose = stockOutService.getStockOutByPurpose(purposeEnum);
        }

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(stockOutByPurpose)
                .build();    //return stock out records

    }

    @RequestMapping(value = "pick_user", method = RequestMethod.GET)
    public Result getStockOutByPickedUser(@RequestParam(value = "accept", defaultValue = "list") String acceptType,
                                          @RequestParam(value = "pick_user") Long pickUserId,
                                          @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        if (null == pickUserId) {
            throw new JsonParseException("pickUserId");
        }
        Object stockOutByPickedUser;
        if (acceptType.equals("page")) {
            //return paging type list
            Pageable pageable = new PageRequest(pageNo, pageSize);
            stockOutByPickedUser = stockOutService.getStockOutByPickedUser(pageable,pickUserId);
        } else {
            //return list
            stockOutByPickedUser = stockOutService.getStockOutByPickedUser(pickUserId);
        }

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(stockOutByPickedUser)
                .build();    //return stock out records
    }

    @RequestMapping(value = "approve_user", method = RequestMethod.GET)
    public Result getStockOutByApprovedUser(@RequestParam(value = "accept", defaultValue = "list") String acceptType,
                                            @RequestParam(value = "approve_user") Long ApprovedUserId,
                                            @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        if (null == ApprovedUserId) {
            throw new JsonParseException("approvedUserId");
        }
        Object stockOutByApprovedUser;
        if (acceptType.equals("page")) {
            //return paging type list
            Pageable pageable = new PageRequest(pageNo, pageSize);
            stockOutByApprovedUser = stockOutService.getStockOutByApprovedUser(pageable, ApprovedUserId);
        } else {
            //return list
            stockOutByApprovedUser = stockOutService.getStockOutByApprovedUser(ApprovedUserId);
        }

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(stockOutByApprovedUser)
                .build();    //return stock out records
    }


    //this is a little challenging, try your best
    @RequestMapping(value = "/~criterion", method = RequestMethod.POST)
    public Result getStockOutByCustomCriterion(@RequestParam(value = "accept", defaultValue = "list") String acceptType,
                                               @RequestBody HashMap<String, Object> criterion,
                                               @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        if (null == criterion) {
            throw new JsonParseException("criterion");
        }
        Object stockByCriterion;
        if (acceptType.equals("page")) {
            Pageable pageable = new PageRequest(pageNo, pageSize);
            stockByCriterion = stockOutService.getStockOutByCriterion(pageable, criterion);
        } else {
            stockByCriterion = stockOutService.getStockOutByCriterion(criterion);
        }

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(stockByCriterion)
                .build();
    }
}
