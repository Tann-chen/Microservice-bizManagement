package com.inventory.service.impl;

import com.inventory.domain.entity.Item;
import com.inventory.domain.enums.ItemStatus;
import com.inventory.repository.ItemRepository;
import com.inventory.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Long createItem(Item item) throws IllegalArgumentException{
        Assert.hasLength(item.getSkuNo(), "SKuNO not empty");
        Assert.isNull(item.getCommodityId(), "Commodity not empty");
        Assert.isNull(item.getStockInId(), "StockInId not empty");
        Assert.isNull(item.getItemStatus(), "Commodity Status not empty");
        Assert.isNull(item.getCostPerItem(), "Cost not empty");
        Item created = itemRepository.save(item);

        return created.getSerialId();
    }

    @Override
    public Page<Item> getAllItems(Pageable pageable) {
        return itemRepository.findItemsByIsAvailableTrue(pageable);
    }

    @Override
    public Page<Item> getAllItemsByItemStatus(ItemStatus itemStatus) {
        return itemRepository.findItemsByCommodityStatus(itemStatus);
    }

    @Override
    public Item updateItem(Long itemId, Item newItemInfo) {
        Item item = itemRepository.findOne(itemId);
        Assert.notNull(item, "item not existed");
        if (!StringUtils.isEmpty(newItemInfo.getSkuNo())) {
            item.setSkuNo(newItemInfo.getSkuNo());
        }
        if (null != newItemInfo.getCommodityId()) {
            item.setCommodityId(newItemInfo.getCommodityId());
        }
        if (null != newItemInfo.getStockInId()) {
            item.setStockInId(newItemInfo.getStockInId());
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
}
