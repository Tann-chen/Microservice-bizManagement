package com.authorize.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user_permission")
public class Permission {

    private String name;

    private Boolean isAvailable;
}
