package com.inventory.controller;

import com.inventory.comm.exception.JsonParseException;
import com.inventory.comm.result.Result;
import com.inventory.comm.result.ResultBuilder;
import com.inventory.domain.entity.Item;
import com.inventory.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/stock")
public class CurrentStockController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/commodity/{commodityId}/items", method = RequestMethod.GET)
    public Result getStockItemsByCommodity(@PathVariable Long commodityId) throws Exception{
        if (null == commodityId) {
            throw new JsonParseException("commodity Id");
        }
        List<Item> itemListByCommodityID = itemService.getStockItemsByCommodityId(commodityId);

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(itemListByCommodityID)
                .build();  //return list of commodities
    }

    @RequestMapping(value = "/stock_in/{stockInId}", method = RequestMethod.GET)
    public Result getStockItemsByStockInId(@PathVariable Long stockInId){
        if (null == stockInId) {
            throw new JsonParseException("stockin ID");
        }
        List<Item> itemListByStockInId = itemService.getStockItemsByStockInId(stockInId);

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(itemListByStockInId)
                .build();  //return list of commodities
    }

    @RequestMapping(value = "/batch/{batchNo}", method = RequestMethod.GET)
    public Result getStockItemsByBatch(@PathVariable String batchNo) throws Exception{
        if (null == batchNo) {
            throw new JsonParseException("batchNo");
        }
        List<Item> itemListByBatchNo = itemService.getStockItemsByBatch(batchNo);

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(itemListByBatchNo)
                .build(); //return list
    }

    @RequestMapping(value = "/snapshot", method = RequestMethod.GET)
    public Result getStockItemsHistorySnapshot(@RequestParam("timestamp")Timestamp time) {
        if (null == time) {
            //current time
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeStr = sdf.format(new Date());
            time = Timestamp.valueOf(timeStr);
        }
        List<Item> itemHistorySnapshot = itemService.getItemsHistorySnapshot(time);

        // return list of items group by commodity
        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(itemHistorySnapshot)
                .build();
    }
}
