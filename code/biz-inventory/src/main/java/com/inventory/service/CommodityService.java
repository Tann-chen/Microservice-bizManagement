package com.inventory.service;

import com.inventory.comm.vo.simCommodity;
import com.inventory.domain.entity.Commodity;
import com.inventory.domain.enums.CommodityType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CommodityService {

    Long createCommodity(Commodity commodity);

    List<Commodity> getAllCommodities();

    List<Commodity> getAllCommoditiesByCommodityType(CommodityType commodityType);

    Commodity updateCommodity(Long commodityId, Commodity newCommodityInfo);

    void deleteCommodity(Long commodityId);

    Commodity getCommoditiesByName(String name);

    List<simCommodity> getCommodityOptions();
}
