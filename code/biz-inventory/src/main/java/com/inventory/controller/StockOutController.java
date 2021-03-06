package com.inventory.controller;


import com.inventory.comm.exception.JsonParseException;
import com.inventory.comm.result.Result;
import com.inventory.comm.result.ResultBuilder;
import com.inventory.domain.entity.StockOut;
import com.inventory.domain.enums.StockOutPurpose;
import com.inventory.domain.remote.User;
import com.inventory.service.StockOutService;
import com.inventory.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stock_out")
public class StockOutController {

    @Autowired
    private StockOutService stockOutService;

    @Autowired
    private UserInfoService userInfoService;


    @RequestMapping(value = "/{stockOutId}", method = RequestMethod.GET)
    public Result getStockOutDetails(@PathVariable Long stockOutId) throws Exception {
        if (null == stockOutId) {
            throw new JsonParseException("stockOut Id");
        }

        Map<String, Object> response = new HashMap<>();

        StockOut stockOutDetail = stockOutService.getStockOutDetail(stockOutId);
        Long approvedUserId = stockOutDetail.getApprovedUser();
        Long pickedUserId = stockOutDetail.getPickedUser();
        User approvedUser = userInfoService.getUserInfoById(approvedUserId);
        User pickedUser = userInfoService.getUserInfoById(pickedUserId);

        response.put("stock_out_info", stockOutDetail);
        response.put("approvedUser", approvedUser);
        response.put("pickedUser", pickedUser);

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(response)
                .build();
    }

    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    public Result getStockOutDetailsByItem(@PathVariable Long itemId) throws Exception {
        if (null == itemId) {
            throw new JsonParseException("item");
        }
        StockOut stockOutDetail = stockOutService.getStockOutDetailByItem(itemId);

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(stockOutDetail)
                .build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result getStockOut(@RequestParam(value = "accept", defaultValue = "list") String acceptType,
                              @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        Object allStockOut;

        if (acceptType.equals("page")) {
            Pageable pageable = new PageRequest(pageNo, pageSize);
            allStockOut = stockOutService.getAllStockOut(pageable);
        } else {
            allStockOut = stockOutService.getAllStockOut();
        }

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(allStockOut)
                .build();
    }

    @RequestMapping(value = "/pick_time", method = RequestMethod.GET)
    public Result getStockOutByPickedTime(@RequestParam(value = "accept", defaultValue = "list") String acceptType,
                                          @RequestParam(value = "from", required = false) Timestamp fromTime,
                                          @RequestParam(value = "to", required = false) Timestamp toTime,
                                          @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (null == fromTime) {
            String preTime = "2000-01-01 00:00:00";
            fromTime = Timestamp.valueOf(preTime);
        }
        if (null == toTime) {
            String curTime = sdf.format(new Date());
            toTime = Timestamp.valueOf(curTime);   //current timestamp
        }
        Object stockOutByPickedTime;
        if (acceptType.equals("page")) {
            Pageable pageable = new PageRequest(pageNo, pageSize);
            stockOutByPickedTime = stockOutService.getStockOutByPickedTime(pageable, fromTime, toTime);
        } else {
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

        Object stockOutByPurpose;

        try {
            // switch from StockOutPurpose enums
            StockOutPurpose purposeEnum = StockOutPurpose.valueOf(purpose.toUpperCase());

            if (acceptType.equals("page")) {
                Pageable pageable = new PageRequest(pageNo, pageSize);
                stockOutByPurpose = stockOutService.getStockOutByPurpose(pageable, purposeEnum);

            } else {
                stockOutByPurpose = stockOutService.getStockOutByPurpose(purposeEnum);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("name of purpose not existed");
        }

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(stockOutByPurpose)
                .build();
    }


    @RequestMapping(value = "/pick_user", method = RequestMethod.GET)
    public Result getStockOutByPickedUser(@RequestParam(value = "accept", defaultValue = "list") String acceptType,
                                          @RequestParam(value = "pick_user") Long pickUserId,
                                          @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        if (null == pickUserId) {
            throw new JsonParseException("pickUserId");
        }
        Object stockOutByPickedUser;

        if (acceptType.equals("page")) {
            Pageable pageable = new PageRequest(pageNo, pageSize);
            stockOutByPickedUser = stockOutService.getStockOutByPickedUser(pageable, pickUserId);
        } else {
            stockOutByPickedUser = stockOutService.getStockOutByPickedUser(pickUserId);
        }

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(stockOutByPickedUser)
                .build();
    }


    @RequestMapping(value = "/approve_user", method = RequestMethod.GET)
    public Result getStockOutByApprovedUser(@RequestParam(value = "accept", defaultValue = "list") String acceptType,
                                            @RequestParam(value = "approve_user") Long ApprovedUserId,
                                            @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        if (null == ApprovedUserId) {
            throw new JsonParseException("approvedUserId");
        }

        Object stockOutByApprovedUser;

        if (acceptType.equals("page")) {
            Pageable pageable = new PageRequest(pageNo, pageSize);
            stockOutByApprovedUser = stockOutService.getStockOutByApprovedUser(pageable, ApprovedUserId);
        } else {
            stockOutByApprovedUser = stockOutService.getStockOutByApprovedUser(ApprovedUserId);
        }

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(stockOutByApprovedUser)
                .build();
    }


    @RequestMapping(value = "/~criterion", method = RequestMethod.POST)
    public Result getStockOutByCustomCriterion(@RequestParam(value = "accept", defaultValue = "list") String acceptType,
                                               @RequestBody HashMap<String, Object> criterion,
                                               @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        if (null == criterion) {
            throw new JsonParseException("criterion");
        }

        //todo: need to change
        Pageable pageable = new PageRequest(pageNo, pageSize);
        Object stockByCriterion = stockOutService.getStockOutByCriterion(pageable, criterion, acceptType);

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(stockByCriterion)
                .build();
    }
}
