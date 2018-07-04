package com.user.domain.enums;

import java.io.Serializable;

public enum  PermissionType implements Serializable{
    CREATE("create"),
    READ("read"),
    UPDATE("update"),
    DELETE("delete"),

    ;
    private String type;
    private static final long serialVersionUID = 1L;

    PermissionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
