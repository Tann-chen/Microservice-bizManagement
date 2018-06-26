package com.inventory.domain.enums;


public enum CommodityType {

    ;
    private String type;

    private CommodityType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
