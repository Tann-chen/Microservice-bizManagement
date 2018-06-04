package com.user.comm.exception;

public enum SysExceptionEnum {
    PARAM_NOT_NULL("input parameters can not be null"),

    ;

    private String reason;

    SysExceptionEnum(String reason){
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
