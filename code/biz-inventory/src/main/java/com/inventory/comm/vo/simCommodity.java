package com.inventory.comm.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SimCommodity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
}
