package com.inventory.domain.enums;

public enum ItemStatus {

    ;
    private String status;

    private ItemStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
