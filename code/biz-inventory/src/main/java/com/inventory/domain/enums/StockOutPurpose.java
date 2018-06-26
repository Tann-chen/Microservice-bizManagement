package com.inventory.domain.enums;

public enum StockOutPurpose {
    CLEANNING("cleaning"),
    SELL("sell"),
    PROCESS("process")
    ;

    private String purpose;

    private StockOutPurpose(String purpose){
        this.purpose = purpose;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
