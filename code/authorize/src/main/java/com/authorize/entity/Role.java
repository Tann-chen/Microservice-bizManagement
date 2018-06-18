package com.authorize.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "user_role")
public class Role implements Serializable {

    private String role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_relation_role_permission",
            joinColumns = {@JoinColumn(referencedColumnName = "id", name = "user_role_id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id", name = "user_permission_id")})
    private List<Permission> permissionList;


    private Boolean isAvailable;
}
