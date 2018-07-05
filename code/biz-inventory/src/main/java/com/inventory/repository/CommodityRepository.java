package com.inventory.repository;

import com.inventory.comm.vo.SimCommodity;
import com.inventory.domain.entity.Commodity;
import com.inventory.domain.enums.CommodityType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommodityRepository extends CrudRepository<Commodity, Long> {

    List<Commodity> findCommoditiesByIsAvailableTrue();

    List<Commodity> findCommoditiesByCommodityTypeAndIsAvailableTrue(CommodityType commodityType);

    Commodity findCommodityByNameAndIsAvailableTrue(String name);

    @Query(value = "SELECT new com.inventory.comm.vo.SimCommodity(c.id, c.name) FROM Commodity c WHERE c.isAvailable = true")
    List<SimCommodity> findCommodityOptions();
}
