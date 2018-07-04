package com.inventory.repository;

import com.inventory.domain.entity.StockOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Timestamp;
import java.util.List;

public interface StockOutRepository extends PagingAndSortingRepository<StockOut, Long>{
    List<StockOut> findAll();

    StockOut findStockOutByItemId(Long itemId);

    List<StockOut> findStockOutsByPickedTimeBetween(Timestamp fromTime, Timestamp toTime);
    Page<StockOut> findStockOutsByPickedTimeBetween(Timestamp fromTime, Timestamp toTime, Pageable pageable);

    List<StockOut> findStockOutsByPurpose(String purpose);
    Page<StockOut> findStockOutsByPurpose(String purpose, Pageable pageable);

    List<StockOut> findStockOutsByPickedUser(Long pickedUserId);
    Page<StockOut> findStockOutsByPickedUser(Long pickedUserId, Pageable pageable);

    List<StockOut> findStockOutsByApprovedUser(Long ApprovedUserId);
    Page<StockOut> findStockOutsByApprovedUser(Long ApprovedUserId, Pageable pageable);
}
