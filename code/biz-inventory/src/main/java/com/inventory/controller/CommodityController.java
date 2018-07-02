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

    @RequestMapping(method = RequestMethod.GET)     //get commodity list
    public Result getCommodityList() {
        return null;    //return list of commodities
    }

    // need to check the name is existed or not
    @RequestMapping(method = RequestMethod.POST)
    public Result createCommodity(Commodity commodity){
        return null;   //return list of commodity after created
    }


    //need to check name is is_existed of not
    @RequestMapping(value = "/{commodityId}", method = RequestMethod.POST)
    public Result updateCommodityInfo(@PathVariable Long commodityId, Commodity commodityInfo){
        return null;    //return new commodity info
    }

    //make the status of commodity disable - "delete"
    @RequestMapping(value = "/{commodityId}", method = RequestMethod.DELETE)
    public Result disableCommodity(@PathVariable Long commodityId){
        return null;    //return list of commodity after disable
    }


    //options : commodity in list only include id and name field
    @RequestMapping(method = RequestMethod.OPTIONS)
    public Result getCommodityOptions(){
        return null;
    }


    @RequestMapping(value = "/name/{commodityName}", method = RequestMethod.GET)
    public Result isExistedOfCommodityName(@PathVariable String commodityName){
        return null; //check the commodity name is existed or not
        // true or false

    }
}
