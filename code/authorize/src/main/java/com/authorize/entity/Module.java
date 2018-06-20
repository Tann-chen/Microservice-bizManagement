package com.authorize.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user_module")
public class Module implements Serializable {

    @Id
    private Integer id;

    private String name;

    private Boolean isAvailable;
}
