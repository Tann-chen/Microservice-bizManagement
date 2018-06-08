package com.user.comm.Result;


public class ResultBuilder {
    public static final int SUCCESS = 200;
    public static final int REDIRECT = 300;
    public static final int FAILED = 400;

    private int code;
    private String message;
    private Object data;


    int getCode() {
        return code;
    }

    String getMessage() {
        return message;
    }

    Object getData() {
        return data;
    }

    public ResultBuilder setCode(int code) {
        this.code = code;
        return this;
    }

    public ResultBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public ResultBuilder setData(Object data) {
        this.data = data;
        return this;
    }

    public Result build(){
        return new Result(this);
    }
}
