package com.inventory.repository;

import com.inventory.domain.entity.Commodity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CommodityRepository extends PagingAndSortingRepository<Commodity, Long> {

    Page<Commodity> findCommoditiesByIsAvailableTrue(Pageable pageable);
}
