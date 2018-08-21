package com.account.comm.exception;

public class JsonParseException extends RuntimeException {
    public JsonParseException(String objName) {
        super("[CONTROLLER] error in parsing json to object : " + objName);
    }
}
