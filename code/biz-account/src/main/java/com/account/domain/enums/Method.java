package com.account.domain.enums;

public enum Method {
    manual("Manual"),
    electronic("Electronic");

    private String name;

    Method(String name){
        this.name = name;
    }
}
