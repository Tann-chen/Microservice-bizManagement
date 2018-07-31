package com.inventory.service.impl;

import com.inventory.comm.queryObj.SimCommodity;
import com.inventory.domain.entity.Commodity;
import com.inventory.domain.enums.CommodityType;
import com.inventory.repository.CommodityRepository;
import com.inventory.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityRepository commodityRepository;

    @Override
    public Long createCommodity(Commodity commodity) throws IllegalArgumentException {
        Assert.hasLength(commodity.getName(), "name not empty");
        Assert.notNull(commodity.getCommodityType(), "commodity type not empty");
        Assert.hasLength(commodity.getQuantityUnit(), "quantity unit not empty");
        Assert.notNull(commodity.getProcessingPeriod(), "processing period not empty");

        if(isExistedCommodityName(commodity.getName())){
            throw new IllegalArgumentException("commodity name has already existed");
        }

        Commodity created = commodityRepository.save(commodity);
        return created.getId();
    }

    @Override
    public List<Commodity> getAllCommodities() {
        return commodityRepository.findCommoditiesByIsAvailableTrue();
    }

    @Override
    public List<Commodity> getAllCommoditiesByCommodityType(CommodityType commodityType) {
        return commodityRepository.findCommoditiesByCommodityTypeAndIsAvailableTrue(commodityType);
    }

    @Override
    public Commodity updateCommodity(Long commodityId, Commodity newCommodityInfo) throws IllegalArgumentException {
        Commodity commodity = commodityRepository.findOne(commodityId);
        Assert.notNull(commodity, "commodity not existed");

        if(isExistedCommodityName(commodity.getName())){
            throw new IllegalArgumentException("commodity name has already existed");
        }

        if (!StringUtils.isEmpty(newCommodityInfo.getName())) {
            commodity.setName(newCommodityInfo.getName());
        }
        if (null != newCommodityInfo.getCommodityType()) {
            commodity.setCommodityType(newCommodityInfo.getCommodityType());
        }
        if (null != newCommodityInfo.getQuantityUnit()) {
            commodity.setQuantityUnit(newCommodityInfo.getQuantityUnit());
        }
        if (null != newCommodityInfo.getProcessingPeriod()) {
            commodity.setProcessingPeriod(newCommodityInfo.getProcessingPeriod());
        }

       return commodityRepository.save(commodity);
    }

    @Override
    public void deleteCommodity(Long commodityId) throws IllegalArgumentException {
        Commodity commodity = commodityRepository.findOne(commodityId);
        Assert.notNull(commodity, "item not exist");

        commodity.setIsAvailable(false);
        commodityRepository.save(commodity);
    }

    @Override
    public List<SimCommodity> getCommodityOptions() {
        return commodityRepository.findCommodityOptions();
    }


    public boolean isExistedCommodityName(String commodityName) throws IllegalArgumentException{
        Assert.hasLength(commodityName, "name not empty");
        Commodity commodity = commodityRepository.findCommodityByNameAndIsAvailableTrue(commodityName);
        return commodity != null;
    }
}
