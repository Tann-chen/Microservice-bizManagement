package com.user.comm.exception;

public class SystemException extends RuntimeException {

    private String message;

    public SystemException(SysExceptionEnum sysExceptionType){
        this.message = sysExceptionType.getReason();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
