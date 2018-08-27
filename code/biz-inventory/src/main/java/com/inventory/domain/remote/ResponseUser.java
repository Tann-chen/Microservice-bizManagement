package com.inventory.domain.remote;

import lombok.Data;

@Data
public class ResponseUser {

    private int code;

    private String message;

    private User data;

}
