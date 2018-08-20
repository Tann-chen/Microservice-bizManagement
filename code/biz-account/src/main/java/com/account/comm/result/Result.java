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


    public class Builder{
        private int code;
        private String message;
        private Object data;

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

        public Result build(){
            return new Result(this);
        }
    }

}
