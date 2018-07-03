package com.inventory.repository;

import com.inventory.comm.vo.simCommodity;
import com.inventory.domain.entity.Commodity;
import com.inventory.domain.enums.CommodityType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CommodityRepository extends CrudRepository<Commodity, Long> {

    List<Commodity> findCommoditiesByIsAvailableTrue();

    List<Commodity> findCommoditiesByCommodityType(CommodityType commodityType);

    Commodity findCommodityByName(String name);

    @Query(value = "SELECT id, name FROM inventory_commodity",nativeQuery=true)
    List<simCommodity> findCommodityOptions();
}
