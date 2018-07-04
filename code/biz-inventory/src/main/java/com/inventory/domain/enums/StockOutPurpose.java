package com.inventory.domain.enums;


public enum StockOutPurpose {
    CLEANING("cleaning"),
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

    public static StockOutPurpose getStockOutPurposeByString(String purpose){
        for(StockOutPurpose stockOutPurpose : StockOutPurpose.values()){
            if(purpose.equals(stockOutPurpose.getPurpose())) {
                return stockOutPurpose;
            }
        }
        return null;
    }
}
