package com.authorize.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
@Entity
@Table(name = "user_info")
public class User implements Serializable{

    private String email;

    private String password;

    private String pwdSalt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_relation_info_role",
            joinColumns = {@JoinColumn(referencedColumnName = "id", name = "user_id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id", name = "user_role_id")})
    private List<Role> roleList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_relation_info_permission",
            joinColumns = {@JoinColumn(referencedColumnName = "id", name = "user_id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id", name = "user_permission_id")})
    private List<Permission> permissionList;

    private Boolean isActive;

    private Boolean isLocked;

}
