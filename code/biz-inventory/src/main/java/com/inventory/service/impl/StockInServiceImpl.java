package com.inventory.service.impl;

import com.inventory.domain.entity.StockIn;
import com.inventory.repository.StockInRepository;
import com.inventory.service.StockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class StockInServiceImpl implements StockInService {

    @Autowired
    private StockInRepository stockInRepository;

    @Override
    public Long createStockIn(StockIn stockIn) throws IllegalArgumentException {
        Assert.hasLength(stockIn.getBatchNo(), "batchNo not empty");
        Assert.isNull(stockIn.getCommodityId(), "commodity not empty");
        Assert.isNull(stockIn.getReceiveUserId(), "receive user not empty");
        Assert.hasLength(stockIn.getNote(), "note not empty");
        StockIn created = stockInRepository.save(stockIn);

        return created.getId();
    }

    @Override
    public StockIn updateStockIn(Long stockInId, StockIn newStockInInfo) {
        StockIn stockIn = stockInRepository.findOne(stockInId);
        Assert.notNull(stockIn, "stockin not exist");
        if (!StringUtils.isEmpty(newStockInInfo.getBatchNo())) {
            stockIn.setBatchNo(newStockInInfo.getBatchNo());
        }
        if (null != newStockInInfo.getCommodityId()) {
            stockIn.setCommodityId(newStockInInfo.getCommodityId());
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

    @Override
    public StockIn getStockInById(Long stockInId) {
        Assert.notNull(stockInId, "stockin Id not null");
        StockIn res = stockInRepository.findOne(stockInId);

        return res;
    }

    @Override
    public StockIn getStockInByBatchNo(Long batchNo) {
        Assert.notNull(batchNo, "batch No not null");
        StockIn res = stockInRepository.findStockInByBatchNo(batchNo);

        return res;
    }

    @Override
    public List<StockIn> getAllStockIn() {
        return null;
    }

    @Override
    public Page<StockIn> getAllStockIn(Pageable pageable) {
        return null;
    }

    @Override
    public List<StockIn> getStockInByPeriod() {
        return null;
    }

    @Override
    public Page<StockIn> getStockInByPeriod(Pageable pageable) {
        return null;
    }

    @Override
    public List<StockIn> getStockInByCommodity() {
        return null;
    }

    @Override
    public Page<StockIn> getStockInByCommodity(Pageable pageable) {
        return null;
    }

    @Override
    public List<StockIn> getStockInByReceiver() {
        return null;
    }

    @Override
    public Page<StockIn> getStockInByReceiver(Pageable pageable) {
        return null;
    }

}
