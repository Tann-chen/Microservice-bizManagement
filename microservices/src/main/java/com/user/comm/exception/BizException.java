package com.user.comm.exception;

public class BizException extends RuntimeException {

    private int code;
    private String message;

    private BizException(String message){
        this.code = 4000;
        this.message = message;
    }

    public BizException(BizExceptionEnum bizExceptionType){
        this.code = bizExceptionType.getCode();
        this.message = bizExceptionType.getMessage();
    }

    public static BizException entityRulesViolation(String message){
        return new BizException(message);
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
