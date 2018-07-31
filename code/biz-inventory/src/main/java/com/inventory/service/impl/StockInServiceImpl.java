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
        Assert.isNull(stockIn.getCommodity(), "commodity not empty");
        Assert.isNull(stockIn.getReceiveUserId(), "receive user not empty");
        Assert.hasLength(stockIn.getNote(), "note not empty");
        StockIn created = stockInRepository.save(stockIn);

        return created.getId();
    }

    @Override
    public StockIn updateStockIn(Long stockInId, StockIn newStockInInfo) throws IllegalArgumentException {
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

        return stockInRepository.save(stockIn);
    }

    @Override
    public StockIn getStockInById(Long stockInId) {
        return stockInRepository.findOne(stockInId);
    }

    @Override
    public StockIn getStockInByBatchNo(String batchNo) {
        return stockInRepository.findStockInByBatchNo(batchNo);
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
        List<StockIn> stockByCommodity = stockInRepository.findStockInsByCommodity_Id(commodityId);
        return stockByCommodity;
    }

    @Override
    public Page<StockIn> getStockInByCommodity(Pageable pageable, Long commodityId) {
        Page<StockIn> stockByCommodity = stockInRepository.findStockInsByCommodity_Id(commodityId, pageable);
        return stockByCommodity;
    }


    @Override
    public List<StockIn> getStockInByReceiver(Long receiverId) {
        List<StockIn> stockByReceiver = stockInRepository.findStockInsByReceiveUserId(receiverId);
        return stockByReceiver;
    }

    @Override
    public Page<StockIn> getStockInByReceiver(Pageable pageable, Long receiverId) {
        Page<StockIn> stockByReceiver = stockInRepository.findStockInsByReceiveUserId(receiverId, pageable);
        return stockByReceiver;
    }

    @Override
    public List<StockIn> getStockInByPeriod(Timestamp fromTime, Timestamp toTime) throws IllegalArgumentException {
        Assert.notNull(fromTime, "fromTime not null");
        Assert.notNull(toTime, "toTime not null");
        List<StockIn> stockByPeriod = stockInRepository.findStockInsByEntryTimeBetween(fromTime, toTime);

        return stockByPeriod;
    }

    @Override
    public Page<StockIn> getStockInByPeriod(Pageable pageable, Timestamp fromTime, Timestamp toTime) throws IllegalArgumentException {
        Assert.notNull(fromTime, "fromTime not null");
        Assert.notNull(toTime, "toTime not null");
        Page<StockIn> stockByPeriod = stockInRepository.findStockInsByEntryTimeBetween(fromTime, toTime, pageable);

        return stockByPeriod;
    }
}
