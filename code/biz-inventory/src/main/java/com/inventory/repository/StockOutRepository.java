package com.inventory.repository;

import com.inventory.domain.entity.StockIn;
import com.inventory.domain.entity.StockOut;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StockOutRepository extends PagingAndSortingRepository<StockOut, Long>{
    List<StockOut> findAll();
}
