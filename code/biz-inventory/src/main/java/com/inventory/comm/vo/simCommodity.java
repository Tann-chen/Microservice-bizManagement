package com.inventory.comm.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class simCommodity implements Serializable{
    private static final long serialVersionUID = 1L;

    Long id;
    String name;

    public simCommodity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
