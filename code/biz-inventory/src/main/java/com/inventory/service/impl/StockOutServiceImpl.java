package com.inventory.service.impl;

import com.inventory.domain.entity.StockOut;
import com.inventory.repository.StockOutRepository;
import com.inventory.service.StockOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

public class StockOutServiceImpl implements StockOutService {

    @Autowired
    private StockOutRepository stockOutRepository;

    @Override
    public Long createStockOut(StockOut stockOut) {
        Assert.notNull(stockOut.getItem(), "item not empty");
        Assert.notNull(stockOut.getPickedUser(), "picked user not empty");
        Assert.notNull(stockOut.getApprovedUser(), "approved user not empty");
        Assert.notNull(stockOut.getPurpose(), "purpose not empty");
        Assert.hasLength(stockOut.getNote(), "note not empty");
        StockOut created = stockOutRepository.save(stockOut);

        return created.getId();
    }

    @Override
    public List<StockOut> getAllStockOut() {
        return (List<StockOut>)stockOutRepository.findAll();
    }

    @Override
    public Page<StockOut> getAllStockOut(Pageable pageable) {
        return stockOutRepository.findAll(pageable);
    }

    @Override
    public StockOut updateStockOut(Long stockOutId, StockOut newStockOutInfo) {
        StockOut stockOut = stockOutRepository.findOne(stockOutId);
        Assert.notNull(stockOut, "stockout no exist");
        if (null != newStockOutInfo.getItem()) {
            stockOut.setItem(newStockOutInfo.getItem());
        }
        if (null != newStockOutInfo.getPickedTime()) {
            stockOut.setPickedTime(newStockOutInfo.getPickedTime());
        }
        if (null != newStockOutInfo.getPickedUser()) {
            stockOut.setPickedUser(newStockOutInfo.getPickedUser());
        }
        if (null != newStockOutInfo.getApprovedUser()) {
            stockOut.setApprovedUser(newStockOutInfo.getApprovedUser());
        }
        if (null != newStockOutInfo.getPurpose()) {
            stockOut.setPurpose(newStockOutInfo.getPurpose());
        }
        if (StringUtils.isEmpty(newStockOutInfo.getNote())) {
            stockOut.setNote(newStockOutInfo.getNote());
        }
        StockOut updated = stockOutRepository.save(stockOut);

        return updated;
    }
}
