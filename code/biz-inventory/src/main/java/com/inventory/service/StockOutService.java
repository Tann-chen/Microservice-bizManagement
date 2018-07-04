package com.inventory.service;

import com.inventory.domain.entity.StockOut;
import com.inventory.domain.enums.StockOutPurpose;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;


public interface StockOutService {

    Long createStockOut(StockOut stockOut);

    StockOut updateStockOut(Long stockOutId, StockOut newStockOutInfo);

    StockOut getStockOutDetail(Long stockOutId);

    StockOut getStockOutDetailByItem(Long itemId);

    List<StockOut> getAllStockOut();
    Page<StockOut> getAllStockOut(Pageable pageable);

    List<StockOut> getStockOutByPickedTime(Timestamp fromTime, Timestamp toTime);
    Page<StockOut> getStockOutByPickedTime(Pageable pageable, Timestamp fromTime, Timestamp toTime);

    List<StockOut> getStockOutByPurpose(StockOutPurpose purpose);
    Page<StockOut> getStockOutByPurpose(Pageable pageable, StockOutPurpose purpose);

    List<StockOut> getStockOutByPickedUser(Long pickedUserId);
    Page<StockOut> getStockOutByPickedUser(Pageable pageable, Long pickedUserId);

    List<StockOut> getStockOutByApprovedUser(Long approvedUser);
    Page<StockOut> getStockOutByApprovedUser(Pageable pageable, Long approvedUser);

    List<StockOut> getStockOutByCriterion(HashMap<String, Object> criterion);
    Page<StockOut> getStockOutByCriterion(Pageable pageable, HashMap<String, Object> criterion);

}
