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

    public static final String CREATE = "create";
    public static final String READ = "read";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id", referencedColumnName="module_id")
    private Module module;

    @Enumerated(EnumType.STRING)
    private PermissionType permission;

    private Boolean isAvailable;

}
