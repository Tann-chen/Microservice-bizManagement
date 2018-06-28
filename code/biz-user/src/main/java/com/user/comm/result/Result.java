package com.user.comm.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class Result implements Serializable{
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private Object data;

    Result(ResultBuilder resultBuilder) {
        this.code = resultBuilder.getCode();
        this.message = resultBuilder.getMessage();
        this.data = resultBuilder.getData();
    }
}
