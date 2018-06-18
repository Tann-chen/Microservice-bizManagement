package com.authorize.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "user_permission")
public class Permission implements Serializable {

    @Getter
    @Setter
    private String permission;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    private Module module;

    @Getter
    @Setter
    private Boolean isAvailable;

    @Override
    public String toString() {
        return this.module.getName() + ":" + this.permission;
    }
}
