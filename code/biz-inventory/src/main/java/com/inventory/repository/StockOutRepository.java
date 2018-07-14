package com.inventory.repository;

import com.inventory.domain.entity.StockOut;
import com.inventory.domain.enums.StockOutPurpose;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Timestamp;
import java.util.List;

public interface StockOutRepository extends PagingAndSortingRepository<StockOut, Long>, JpaSpecificationExecutor<StockOut>{
    List<StockOut> findAll();

    StockOut findStockOutByItem_SerialId(Long itemId);

    List<StockOut> findStockOutsByPickedTimeBetween(Timestamp fromTime, Timestamp toTime);
    Page<StockOut> findStockOutsByPickedTimeBetween(Timestamp fromTime, Timestamp toTime, Pageable pageable);

    List<StockOut> findStockOutsByPurpose(StockOutPurpose purpose);
    Page<StockOut> findStockOutsByPurpose(StockOutPurpose purpose, Pageable pageable);

    List<StockOut> findStockOutsByPickedUser(Long pickedUserId);
    Page<StockOut> findStockOutsByPickedUser(Long pickedUserId, Pageable pageable);

    List<StockOut> findStockOutsByApprovedUser(Long ApprovedUserId);
    Page<StockOut> findStockOutsByApprovedUser(Long ApprovedUserId, Pageable pageable);

}
