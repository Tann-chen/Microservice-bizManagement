package com.inventory.domain.enums;


public enum CommodityType {
    RAW_MATERIAL("raw_material"),
    PRODUCTION("production"),
    SEMI_FINISHED("semi_finished")
    ;
    private String type;

    private CommodityType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
