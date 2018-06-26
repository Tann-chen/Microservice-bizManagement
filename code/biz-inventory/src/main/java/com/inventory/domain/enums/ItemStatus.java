package com.inventory.domain.enums;

public enum ItemStatus {
    IN_STOCK("in_stock"),
    SOLD("sold"),
    READY_FOR_PRODUCTION("ready_for_production"),
    BROKEN("broken"),
    PICKED_FOR_PROCESS("picked_for_process")
    ;
    private String status;

    private ItemStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
