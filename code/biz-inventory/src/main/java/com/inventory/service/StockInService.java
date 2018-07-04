package com.inventory.service;

import com.inventory.domain.entity.StockIn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


public interface StockInService {

    Long createStockIn(StockIn stockIn);

    StockIn updateStockIn(Long stockInId, StockIn newStockInInfo);

    StockIn getStockInById(Long stockInId);

    StockIn getStockInByBatchNo(Long batchNo);

    List<StockIn> getAllStockIn();
    Page<StockIn> getAllStockIn(Pageable pageable);

    List<StockIn> getStockInByPeriod(Timestamp fromTime, Timestamp toTime);
    Page<StockIn> getStockInByPeriod(Pageable pageable, Timestamp fromTime, Timestamp toTime);

    List<StockIn> getStockInByCommodity(Long commodityId);
    Page<StockIn> getStockInByCommodity(Pageable pageable, Long commodityId);

    List<StockIn> getStockInByReceiver(Long receiverId);
    Page<StockIn> getStockInByReceiver(Pageable pageable, Long receiverId);

}
