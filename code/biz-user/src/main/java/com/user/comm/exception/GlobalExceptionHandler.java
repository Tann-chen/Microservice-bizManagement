package com.user.comm.exception;

import com.user.comm.result.Result;
import com.user.comm.result.ResultBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(value = IllegalArgumentException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    private Result exceptionHandler(Exception e) {
//        return new ResultBuilder()
//                .setCode(400)
//                .setMessage(e.getMessage())
//                .build();
//    }
}
