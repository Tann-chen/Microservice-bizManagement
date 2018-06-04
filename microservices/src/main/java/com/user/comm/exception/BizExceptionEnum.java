package com.user.comm.exception;

import org.omg.CORBA.OBJECT_NOT_EXIST;

public enum BizExceptionEnum {
    EMAIL_ALREADY_EXIST(4001, "The email has already existed"),
    OBJECT_NOT_EXIST(4002, "The object is not existed"),
    ;


    private int code;
    private String message;

    BizExceptionEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
