package com.inventory.service;

import com.inventory.domain.entity.StockIn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StockInService {

    Long createStockIn(StockIn stockIn);

    StockIn updateStockIn(Long stockInId, StockIn newStockInInfo);

    StockIn getStockInById(Long stockInId);

    StockIn getStockInByBatchNo(Long batchNo);

    List<StockIn> getAllStockIn();
    Page<StockIn> getAllStockIn(Pageable pageable);

    List<StockIn> getStockInByPeriod();
    Page<StockIn> getStockInByPeriod(Pageable pageable);

    List<StockIn> getStockInByCommodity();
    Page<StockIn> getStockInByCommodity(Pageable pageable);

    List<StockIn> getStockInByReceiver();
    Page<StockIn> getStockInByReceiver(Pageable pageable);

}
