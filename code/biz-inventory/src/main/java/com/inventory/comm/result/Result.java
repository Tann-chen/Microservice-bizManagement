package com.inventory.comm.result;

import lombok.Data;

@Data
public class Result {
    private int code;
    private String message;
    private Object data;

    Result(ResultBuilder resultBuilder) {
        this.code = resultBuilder.getCode();
        this.message = resultBuilder.getMessage();
        this.data = resultBuilder.getData();
    }
}
