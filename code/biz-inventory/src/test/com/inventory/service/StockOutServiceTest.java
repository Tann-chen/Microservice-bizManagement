package com.inventory.service;

import com.inventory.domain.entity.Item;
import com.inventory.domain.entity.StockOut;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: Tim.L
 * @Date: created in 21:08 2018/7/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StockOutServiceTest {
    @Autowired
    private StockOutService stockOutService;

    @Test
    public void getStockOutByCriterion() throws Exception {
        HashMap<String, Object> criterion = new HashMap<>();
        criterion.put("pickedUser", 16789);
        criterion.put("itemId", 1110002);

        List<StockOut> res = stockOutService.getStockOutByCriterion(criterion);

        System.out.println(res);

    }


}