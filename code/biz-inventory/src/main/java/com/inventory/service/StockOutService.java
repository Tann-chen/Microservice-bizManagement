package com.inventory.service;

import com.inventory.domain.entity.StockOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StockOutService {

    Long createStockOut(StockOut stockOut);

    List<StockOut> getAllStockOut();

    Page<StockOut> getAllStockOut(Pageable pageable);

    StockOut updateStockOut(Long stockOutId, StockOut newStockOutInfo);
}
