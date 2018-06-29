package com.inventory.service;

import com.inventory.domain.entity.StockIn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StockInService {

    Long createStockIn(StockIn stockIn);

    List<StockIn> getAllStockIn();

    Page<StockIn> getAllStockIn(Pageable pageable);

    StockIn updateStockIn(Long stockInId, StockIn newStockInInfo);
}
