package com.inventory.repository;

import com.inventory.domain.entity.Item;
import com.inventory.domain.enums.ItemStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {

    List<Item> findItemsByIsAvailableTrue();

    List<Item> findItemsByItemStatus(ItemStatus itemStatus);

    List<Item> findItemsByCommodityId(Long commodityId);

    List<Item> findItemsByStockInId(Long stockInId);



}
