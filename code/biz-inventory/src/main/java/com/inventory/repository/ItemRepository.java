package com.inventory.repository;

import com.inventory.domain.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {

    Page<Item> findItemByIsAvailableTrue(Pageable pageable);

}
