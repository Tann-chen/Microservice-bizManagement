package com.inventory.service.impl;

import com.inventory.domain.entity.Item;
import com.inventory.domain.enums.ItemStatus;
import com.inventory.repository.ItemRepository;
import com.inventory.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Long createItem(Item item) throws IllegalArgumentException{
        Assert.hasLength(item.getSkuNo(), "SKuNO not empty");
        Assert.isNull(item.getCommodity(), "Commodity not empty");
        Assert.isNull(item.getStockIn(), "StockInId not empty");
        Assert.isNull(item.getItemStatus(), "Commodity Status not empty");
        Assert.isNull(item.getCostPerItem(), "Cost not empty");
        Item created = itemRepository.save(item);

        return created.getSerialId();
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findItemsByIsAvailableTrue();
    }

    @Override
    public List<Item> getAllItemsByItemStatus(ItemStatus itemStatus) {
        return itemRepository.findItemsByItemStatusAndIsAvailableTrue(itemStatus);
    }

    @Override
    public List<Item> getStockItemsByCommodityId(Long commodityId) {
        Assert.notNull(commodityId, "commodity id not null");
        List<Item> itemsByCommodityId = itemRepository.findItemsByCommodity_IdAndIsAvailableTrue(commodityId);

        return itemsByCommodityId;
    }

    @Override
    public List<Item> getStockItemsByStockInId(Long stockInId) {
        Assert.notNull(stockInId, "stockin ID not null");
        List<Item> itemsByStockInId = itemRepository.findItemsByStockIn_IdAndIsAvailableTrue(stockInId);

        return itemsByStockInId;
    }

    @Override
    public List<Item> getStockItemsByBatch(String batchNo) {
        Assert.notNull(batchNo, "batch No Not null");
        List<Item> itemsByBatchNo = itemRepository.findItemsByStockIn_BatchNoAndIsAvailableTrue(batchNo);

        return itemsByBatchNo;
    }


    @Override
    public Item updateItem(Long itemId, Item newItemInfo) {
        Item item = itemRepository.findOne(itemId);
        Assert.notNull(item, "item not existed");
        if (!StringUtils.isEmpty(newItemInfo.getSkuNo())) {
            item.setSkuNo(newItemInfo.getSkuNo());
        }
        if (null != newItemInfo.getCommodity()) {
            item.setCommodity(newItemInfo.getCommodity());
        }
        if (null != newItemInfo.getStockIn()) {
            item.setStockIn(newItemInfo.getStockIn());
        }
        if (null != newItemInfo.getItemStatus()) {
            item.setItemStatus(newItemInfo.getItemStatus());
        }
        if (null != newItemInfo.getCostPerItem()) {
            item.setCostPerItem(newItemInfo.getCostPerItem());
        }
        Item updated = itemRepository.save(item);

        return updated;
    }

    @Override
    public void deleteItem(Long itemId) {
        Item item = itemRepository.findOne(itemId);
        Assert.notNull(item, "item not exist");
        item.setIsAvailable(false);
        itemRepository.save(item);
    }

    @Override
    public Double getCostOfItem(Long itemId) {
        Item item = itemRepository.findOne(itemId);
        Assert.notNull(item, "item not exist");

        return item.getCostPerItem();
    }

    @Override
    public List<Item> getItemsHistorySnapshot(Timestamp time) {
        Assert.notNull(time, "timeStamp not null");
        List<Item> itemListSnapshot = itemRepository.findItemsByStockIn_EntryTimeLessThanAndIsAvailableTrue(time);

        return itemListSnapshot;
    }
}
