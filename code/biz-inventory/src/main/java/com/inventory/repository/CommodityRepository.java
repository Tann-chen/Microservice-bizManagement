package com.inventory.repository;

import com.inventory.domain.entity.Commodity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface CommodityRepository extends CrudRepository<Commodity, Long> {

    List<Commodity> findCommoditiesByIsAvailableTrue(Pageable pageable);
}
