package com.inventory.service;


import com.inventory.comm.queryObj.SimCommodity;
import com.inventory.domain.entity.Commodity;
import com.inventory.domain.enums.CommodityType;
import java.util.List;


public interface CommodityService {

    Long createCommodity(Commodity commodity) throws IllegalArgumentException;

    List<Commodity> getAllCommodities();

    List<Commodity> getAllCommoditiesByCommodityType(CommodityType commodityType);

    Commodity updateCommodity(Long commodityId, Commodity newCommodityInfo) throws IllegalArgumentException;

    void deleteCommodity(Long commodityId) throws IllegalArgumentException;

    boolean isExistedCommodityName(String commodityName) throws IllegalArgumentException;

    List<SimCommodity> getCommodityOptions();
}
