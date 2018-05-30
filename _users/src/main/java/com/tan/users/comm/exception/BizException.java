package com.tan.users.comm.exception;

public class BizException extends RuntimeException {

    private int code;
    private String message;

    public BizException(BizExceptionEnum bizExceptionType){
        this.code = bizExceptionType.getCode();
        this.message = bizExceptionType.getMessage();
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
