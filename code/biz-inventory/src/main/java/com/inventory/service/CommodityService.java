package com.inventory.service;

import com.inventory.comm.vo.SimCommodity;
import com.inventory.domain.entity.Commodity;
import com.inventory.domain.enums.CommodityType;

import java.util.List;


public interface CommodityService {

    Long createCommodity(Commodity commodity);

    List<Commodity> getAllCommodities();

    List<Commodity> getAllCommoditiesByCommodityType(CommodityType commodityType);

    Commodity updateCommodity(Long commodityId, Commodity newCommodityInfo);

    void deleteCommodity(Long commodityId);

    Commodity getCommoditiesByName(String name);

    List<SimCommodity> getCommodityOptions();
}
