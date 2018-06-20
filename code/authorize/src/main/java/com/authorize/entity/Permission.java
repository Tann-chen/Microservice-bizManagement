package com.authorize.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "user_permission")
public class Permission implements Serializable {

    @Id
    private Long id;

    private String permission;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "module_id", referencedColumnName = "id")
    private Module module;

    private Boolean isAvailable;


    public String getAuthority() {
        return this.module.getName() + "_" + this.permission;
    }

}
