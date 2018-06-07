package com.user.comm.Result;

public class Result {
    private Integer code;
    private String message;
    private Object data;

    Result(ResultBuilder resultBuilder) {
        this.code = resultBuilder.getCode();
        this.message = resultBuilder.getMessage();
        this.data = resultBuilder.getData();
    }
}
