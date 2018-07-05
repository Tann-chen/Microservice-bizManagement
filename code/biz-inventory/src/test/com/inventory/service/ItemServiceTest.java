package com.inventory.service;

import com.inventory.domain.entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: Tim.L
 * @Date: created in 20:09 2018/7/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void getStockItemsByCommodityId() throws Exception {
        List<Item> res = itemService.getStockItemsByCommodityId(new Long(96743));
        System.out.println(res);
    }

    @Test
    public void getStockItemsByStockInId() throws Exception {

    }

    @Test
    public void getStockItemsByBatch() throws Exception {
    }

    @Test
    public void getItemsHistorySnapshot() throws Exception {
        List<Item> res = itemService.getItemsHistorySnapshot(Timestamp.valueOf("2017-02-03 11:00:00"));
        System.out.println(res);
    }

}