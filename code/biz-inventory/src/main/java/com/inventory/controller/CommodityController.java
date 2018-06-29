package com.inventory.controller;


import com.inventory.comm.result.Result;
import com.inventory.domain.entity.Commodity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @RequestMapping(method = RequestMethod.GET)
    public Result getCommodityList() {
        return null;    //return list of commodities
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result createCommodity(Commodity commodity){
        return null;   //return list of commodity after created
    }

    @RequestMapping(value = "/{commodityId}", method = RequestMethod.POST)
    public Result updateCommodityInfo(@PathVariable Long commodityId, Commodity commodityInfo){
        return null;    //return new commodity info
    }

    @RequestMapping(value = "/{commodityId}", method = RequestMethod.DELETE)
    public Result disableCommodity(@PathVariable Long commodityId){
        return null;
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public Result getCommodityOptions(){
        return null;    //leave this one to me
    }
}
