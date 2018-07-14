package com.inventory.service;

import com.inventory.domain.entity.Item;
import com.inventory.domain.enums.ItemStatus;

import java.sql.Timestamp;
import java.util.List;


public interface ItemService {

    Long createItem(Item item);

    List<Item> getAllItems();

    List<Item> getAllItemsByItemStatus(ItemStatus itemStatus);

    List<Item> getStockItemsByCommodityId(Long commodityId);

    List<Item> getStockItemsByStockInId(Long stockInId);

    List<Item> getStockItemsByBatch(String batchNo);

    List<Item> getItemsHistorySnapshot(Timestamp time);

    Item updateItem(Long itemId, Item newItemInfo);

    void deleteItem(Long itemId);

    Double getCostOfItem(Long itemId);
}
