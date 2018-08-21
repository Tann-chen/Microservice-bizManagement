package com.account.comm.result;

import lombok.Data;

@Data
public class Result {
    private int code;
    private String message;
    private Object data;

    private Result(Builder resultBuilder) {
        this.code = resultBuilder.getCode();
        this.message = resultBuilder.getMessage();
        this.data = resultBuilder.getData();
    }

    public static class Builder {
        public static final int SUCCESS = 200;
        public static final int REDIRECT = 300;
        public static final int FAILED = 400;

        private int code;
        private String message;
        private Object data;

        public Builder() {
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public Object getData() {
            return data;
        }

        public Builder setCode(int code) {
            this.code = code;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setData(Object data) {
            this.data = data;
            return this;
        }

        public Result build() {
            return new Result(this);
        }
    }

}
