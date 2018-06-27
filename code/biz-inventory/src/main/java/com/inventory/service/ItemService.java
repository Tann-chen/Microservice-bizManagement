package com.inventory.service;

import com.inventory.domain.entity.Item;
import com.inventory.domain.enums.ItemStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ItemService {

    Long createItem(Item item);

    Page<Item> getAllItems(Pageable pageable);

    Page<Item> getAllItemsByItemStatus(ItemStatus itemStatus);

    Item updateItem(Long itemId, Item newItemInfo);

    void deleteItem(Long itemId);

    Double getCostOfItem(Long itemId);
}
