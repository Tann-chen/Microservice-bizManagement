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

import java.sql.Timestamp;
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
        StockIn allStockin = stockInRepository.findOne(stockInId);

        return allStockin;
    }

    @Override
    public StockIn getStockInByBatchNo(Long batchNo) {
        Assert.notNull(batchNo, "batch No not null");
        StockIn allStockin = stockInRepository.findStockInByBatchNo(batchNo);

        return allStockin;
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
    public List<StockIn> getStockInByCommodity(Long commodityId) {
        Assert.notNull(commodityId, "commodity id not null");
        List<StockIn> stockByCommodity = stockInRepository.findStockInsByCommodityId(commodityId);

        return stockByCommodity;
    }

    @Override
    public Page<StockIn> getStockInByCommodity(Pageable pageable, Long commodityId) {
        Assert.notNull(commodityId, "commodity not null");
        Page<StockIn> stockByCommodity = stockInRepository.findStockInsByCommodityId(commodityId, pageable);

        return stockByCommodity;
    }


    @Override
    public List<StockIn> getStockInByReceiver(Long receiverId) {
        Assert.notNull(receiverId, "receiver Id not null");
        List<StockIn> stockByReceiver = stockInRepository.findStockInsByReceiveUserId(receiverId);

        return stockByReceiver;
    }

    @Override
    public Page<StockIn> getStockInByReceiver(Pageable pageable, Long receiverId) {
        Assert.notNull(receiverId, "receiver Id not null");
        Page<StockIn> stockByReceiver = stockInRepository.findStockInsByReceiveUserId(receiverId, pageable);

        return stockByReceiver;
    }

    @Override
    public List<StockIn> getStockInByPeriod(Timestamp fromTime, Timestamp toTime) {
        Assert.notNull(fromTime, "fromTime not null");
        Assert.notNull(toTime, "toTime not null");
        List<StockIn> stockByPeriod = stockInRepository.findStockInsByEntryTimeBetween(fromTime, toTime);

        return stockByPeriod;
    }

    @Override
    public Page<StockIn> getStockInByPeriod(Pageable pageable, Timestamp fromTime, Timestamp toTime) {
        Assert.notNull(fromTime, "fromTime not null");
        Assert.notNull(toTime, "toTime not null");
        Page<StockIn> stockByPeriod = stockInRepository.findStockInsByEntryTimeBetween(fromTime, toTime, pageable);

        return stockByPeriod;
    }
}
