package com.inventory.service;

import com.inventory.domain.entity.Commodity;
import com.inventory.domain.enums.CommodityType;
import com.inventory.repository.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityRepository commodityRepository;

    @Override
    public Long createCommodity(Commodity commodity) throws IllegalArgumentException{
        Assert.hasLength(commodity.getName(), "name not empty");
        Assert.notNull(commodity.getCommodityType(), "commodity type not empty");
        Assert.hasLength(commodity.getQuantityUnit(), "quantity unit not empty");
        Assert.notNull(commodity.getProcessingPeriod(), "processing period not empty");
        Commodity created = commodityRepository.save(commodity);

        return  created.getId();
    }

    @Override
    public List<Commodity> getAllCommodities() {
        return null;
    }

    @Override
    public Page<Commodity> getAllCommodities(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Commodity> getAllCommoditiesByCommodityType(CommodityType commodityType, Pageable pageable) {
        return null;
    }

    @Override
    public Commodity updateCommodity(Long commodityId, Commodity newCommodityInfo) {
        return null;
    }

    @Override
    public void deleteCommodity(Long commodityId) {

    }
}
