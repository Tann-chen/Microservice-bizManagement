package com.user.domain.enums;

public enum  PermissionType {
    CREATE("create"),
    READ("read"),
    UPDATE("update"),
    DELETE("delete"),

    ;
    private String type;

    PermissionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
