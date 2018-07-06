package com.inventory.repository;

import com.inventory.domain.entity.Commodity;
import com.inventory.domain.enums.CommodityType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CommodityRepository extends PagingAndSortingRepository<Commodity, Long> {

    List<Commodity> findCommoditiesByIsAvailableTrue();

    Page<Commodity> findCommoditiesByCommodityType(CommodityType commodityType, Pageable pageable);

    Commodity findCommodityByName(String name);

//    @Query(value = "SELECT id, name FROM inventory_commodity",nativeQuery=true)
//    List<SimCommodity> findCommodityOptions();
}
