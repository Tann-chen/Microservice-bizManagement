package com.inventory.repository;

import com.inventory.domain.entity.Item;
import com.inventory.domain.enums.ItemStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Timestamp;
import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {

    List<Item> findItemsByIsAvailableTrue();

    List<Item> findItemsByItemStatusAndIsAvailableTrue(ItemStatus itemStatus);

    List<Item> findItemsByCommodity_IdAndIsAvailableTrue(Long commodityId);

    List<Item> findItemsByStockIn_IdAndIsAvailableTrue(Long stockInId);

    List<Item> findItemsByStockIn_BatchNoAndIsAvailableTrue(String batchNo);

    List<Item> findItemsByStockIn_EntryTimeLessThanAndIsAvailableTrueOrderByCommodity(Timestamp timestamp);

}
