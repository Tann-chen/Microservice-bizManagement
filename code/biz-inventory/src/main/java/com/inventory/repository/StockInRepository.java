package com.inventory.repository;

import com.inventory.domain.entity.StockIn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Timestamp;
import java.util.List;

public interface StockInRepository extends PagingAndSortingRepository<StockIn, Long>{

    List<StockIn> findAll();
    Page<StockIn> findAll(Pageable pageable);

    List<StockIn> findStockInsByCommodityId(Long commodityId);
    Page<StockIn> findStockInsByCommodityId(Long commodityId,Pageable Pageable);

    List<StockIn> findStockInsByReceiveUserId(Long receiverId);
    Page<StockIn> findStockInsByReceiveUserId(Long receiverId,Pageable Pageable);

    List<StockIn> findStockInsByEntryTimeBetween(Timestamp fromTime, Timestamp toTime);
    Page<StockIn> findStockInsByEntryTimeBetween(Timestamp fromTime, Timestamp toTime, Pageable pageable);

    StockIn findStockInByBatchNo(Long batchNo);

}
