package com.account.comm.exception;

import com.account.comm.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    private Result argumentExceptionHandler(Exception e) {
        return new Result.Builder().setCode(Result.Builder.FAILED).setMessage(e.getMessage()).build();
    }
}

