package com.inventory.repository;

import com.inventory.domain.entity.StockIn;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StockInRepository extends PagingAndSortingRepository<StockIn, Long>{
}
