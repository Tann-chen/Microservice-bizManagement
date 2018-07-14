package com.inventory.comm.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SimCommodity implements Serializable{
    private static final long serialVersionUID = 1L;

    Long id;
    String name;

    public SimCommodity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
