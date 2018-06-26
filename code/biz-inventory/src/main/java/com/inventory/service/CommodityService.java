package com.inventory.service;

import com.inventory.domain.entity.Commodity;
import com.inventory.domain.enums.CommodityType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommodityService {

    Long createCommodity(Commodity commodity);

    List<Commodity> getAllCommodities();

    Page<Commodity> getAllCommodities(Pageable pageable);

    Page<Commodity> getAllCommoditiesByCommodityType(CommodityType commodityType, Pageable pageable);

    Commodity updateCommodity(Long commodityId, Commodity newCommodityInfo);

    void deleteCommodity(Long commodityId);
}
