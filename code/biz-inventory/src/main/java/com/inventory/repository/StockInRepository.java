package com.inventory.repository;

import com.inventory.domain.entity.StockIn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StockInRepository extends PagingAndSortingRepository<StockIn, Long>{

    List<StockIn> findAll();
    Page<StockIn> findAll(Pageable pageable);

    List<StockIn> findStockInsByCommodityId(Long commodityId);
    Page<StockIn>




    StockIn findStockInByBatchNo(Long batchNo);

}
