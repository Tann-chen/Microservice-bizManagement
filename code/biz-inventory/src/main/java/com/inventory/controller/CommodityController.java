package com.inventory.controller;


import com.inventory.comm.exception.JsonParseException;
import com.inventory.comm.result.Result;
import com.inventory.comm.result.ResultBuilder;
import com.inventory.comm.vo.SimCommodity;
import com.inventory.domain.entity.Commodity;
import com.inventory.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @RequestMapping(method = RequestMethod.GET)     //get commodity list
    public Result getCommodityList() {
        List<Commodity> commodityList = commodityService.getAllCommodities();

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(commodityList)
                .build();    //return list of commodities
    }

    // need to check the name is existed or not
    @RequestMapping(method = RequestMethod.POST)
    public Result createCommodity(@RequestBody Commodity commodity) throws Exception {
        if (null == commodity) {
            throw new JsonParseException("commodity");
        }
        Long newCommodityId = commodityService.createCommodity(commodity);
        List<Commodity> commodityList = commodityService.getAllCommodities();

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(commodityList)
                .build();   //return list of commodity after created
    }


    //need to check name is is_existed of not
    @RequestMapping(value = "/{commodityId}", method = RequestMethod.POST)
    public Result updateCommodityInfo(@PathVariable Long commodityId, Commodity commodityInfo) throws Exception {
        if (null == commodityId) {
            throw new JsonParseException("commodity ID");
        }
        if (null == commodityInfo) {
            throw new JsonParseException("commodity");
        }
        Commodity newCommodityInfo = commodityService.updateCommodity(commodityId, commodityInfo);

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(newCommodityInfo)
                .build();    //return new commodity info
    }

    //make the status of commodity disable - "delete"
    @RequestMapping(value = "/{commodityId}", method = RequestMethod.DELETE)
    public Result disableCommodity(@PathVariable Long commodityId) throws Exception{
        if (null == commodityId) {
            throw new JsonParseException("commodity Id");
        }
        commodityService.deleteCommodity(commodityId);
        List<Commodity> commodityList = commodityService.getAllCommodities();

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(commodityList)
                .build();    //return list of commodity after disable
    }


    //options : commodity in list only include id and name field
    @RequestMapping(method = RequestMethod.OPTIONS)
    public Result getCommodityOptions() throws Exception {

        List <SimCommodity> commodityOptionsList = commodityService.getCommodityOptions();

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(commodityOptionsList)
                .build();
    }

    @RequestMapping(value = "/name/{commodityName}", method = RequestMethod.GET)
    public Result isExistedOfCommodityName(@PathVariable String commodityName) throws Exception {
        if (null == commodityName) {
            throw new JsonParseException("commodity name");
        }
        Commodity res = commodityService.getCommoditiesByName(commodityName);
        Boolean exist = (null == res) ? false : true;

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(exist)
                .build(); //check the commodity name is existed or not
        // true or false
    }
}
