package com.inventory.repository;

import com.inventory.domain.entity.Item;
import com.inventory.domain.enums.ItemStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {

    Page<Item> findItemsByIsAvailableTrue(Pageable pageable);

    Page<Item> findItemsByCommodityStatus(ItemStatus commodityStatus, Pageable pageable);

}
