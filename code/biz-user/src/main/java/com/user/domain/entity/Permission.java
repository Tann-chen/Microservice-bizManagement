package com.user.domain.entity;
import com.user.domain.enums.PermissionType;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "user_permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="module_id", referencedColumnName="id")
    private Module module;

    @Enumerated(EnumType.STRING)
    private PermissionType permission;

    private Boolean isAvailable;


    @PrePersist
    public void prePersist(){
        this.isAvailable = true;
    }

}
