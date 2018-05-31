package com.user.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "user_role")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 9)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 90, unique = true)
    private String role;

    @Column(columnDefinition = "text")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_relation_role_permission",
            joinColumns = {@JoinColumn(referencedColumnName = "id", name = "user_role_id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id", name = "user_permission_id")})
    private List<Permission> permissionList;

    @Basic
    private Boolean isAvailable;
}
