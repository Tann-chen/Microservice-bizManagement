package com.inventory.service;

import com.inventory.domain.entity.StockOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StockOutService {

    Long createStockOut(StockOut stockOut);

    List<StockOut> getAllStockOut();

    Page<StockOut> getAllStockOut(Pageable pageable);

    StockOut updateStockOut(Long stockOutId, StockOut newStockOutInfo);
}
