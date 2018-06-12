package com.user.domain.entity;
import com.user.domain.enums.ResourceType;
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

    private String name;

    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;

    private String resourceUrl;

    private String permission;

    private Long parentId;

    private String parentIds;

    private Boolean isAvailable;

}
