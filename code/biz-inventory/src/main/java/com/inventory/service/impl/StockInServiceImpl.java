package com.inventory.service.impl;

import com.inventory.domain.entity.StockIn;
import com.inventory.repository.StockInRepository;
import com.inventory.service.StockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

public class StockInServiceImpl implements StockInService {

    @Autowired
    private StockInRepository stockInRepository;

    @Override
    public Long createStockIn(StockIn stockIn) throws IllegalArgumentException {
        Assert.hasLength(stockIn.getBatchNo(), "batchNo not empty");
        Assert.isNull(stockIn.getCommodity(), "commodity not empty");
        Assert.isNull(stockIn.getReceiveUserId(), "receive user not empty");
        Assert.hasLength(stockIn.getNote(), "note not empty");
        StockIn created = stockInRepository.save(stockIn);

        return created.getId();
    }

    @Override
    public List<StockIn> getAllStockIn() {
        return stockInRepository.findAll();
    }

    @Override
    public Page<StockIn> getAllStockIn(Pageable pageable) {
        return stockInRepository.findAll(pageable);
    }

    @Override
    public StockIn updateStockIn(Long stockInId, StockIn newStockInInfo) {
        StockIn stockIn = stockInRepository.findOne(stockInId);
        Assert.notNull(stockIn, "stockin not exist");
        if (!StringUtils.isEmpty(newStockInInfo.getBatchNo())) {
            stockIn.setBatchNo(newStockInInfo.getBatchNo());
        }
        if (null != newStockInInfo.getCommodity()) {
            stockIn.setCommodity(newStockInInfo.getCommodity());
        }
        if (null != newStockInInfo.getReceiveUserId()) {
            stockIn.setReceiveUserId(newStockInInfo.getReceiveUserId());
        }
        if (!StringUtils.isEmpty(newStockInInfo.getNote())) {
            stockIn.setNote(newStockInInfo.getNote());
        }
        if (null != newStockInInfo.getEntryTime()) {
            stockIn.setEntryTime(newStockInInfo.getEntryTime());
        }
        StockIn updated = stockInRepository.save(stockIn);

        return updated;
    }
}
