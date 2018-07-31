package com.inventory.service;

import com.inventory.comm.queryObj.SimCommodity;
import com.inventory.domain.entity.Commodity;
import com.inventory.domain.enums.CommodityType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CommodityServiceTest {

    @Autowired
    private CommodityService commodityService;

    @Test
    public void createCommodity() throws Exception {
    }

    @Test
    public void getAllCommodities() throws Exception {
        List<Commodity> res= commodityService.getAllCommodities();
        System.out.println(res);
    }

    @Test
    public void getAllCommoditiesByCommodityType() throws Exception {
        List<Commodity> res = commodityService.getAllCommoditiesByCommodityType(CommodityType.PRODUCTION);
        System.out.println(res);
    }

    @Test
    public void updateCommodity() throws Exception {
    }

    @Test
    public void deleteCommodity() throws Exception {
    }

    @Test
    public void getCommoditiesByName() throws Exception {
        Commodity res = commodityService.getCommoditiesByName("Samsung Duo");
        System.out.println(res);
    }

    @Test
    public void getCommodityOptions() throws Exception {
        List<SimCommodity> res = commodityService.getCommodityOptions();
        System.out.println(res);
    }

}