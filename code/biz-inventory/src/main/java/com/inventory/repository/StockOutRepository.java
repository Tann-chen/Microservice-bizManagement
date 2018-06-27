package com.inventory.repository;

import com.inventory.domain.entity.StockOut;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StockOutRepository extends PagingAndSortingRepository<StockOut, Long>{
}
