package com.user.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    private String description;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_relation_role_permission",
            joinColumns = {@JoinColumn(referencedColumnName = "id", name = "user_role_id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id", name = "user_permission_id")})
    private List<Permission> permissionList;

    @JsonIgnore
    private Boolean isAvailable;

    @PrePersist
    public void prePersist() {
        this.isAvailable = true;
    }
}
