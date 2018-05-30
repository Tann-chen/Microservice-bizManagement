package com.user.domain.enums;

public enum ResourceType {
    MENU("menu"),
    BUTTON("button"),
    ;

    private String type;

    ResourceType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
