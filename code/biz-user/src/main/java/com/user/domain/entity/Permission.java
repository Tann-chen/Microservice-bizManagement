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

    @Column(length = 90, unique = true)
    private String name;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;

    @Column(length = 150, nullable = false)
    private String resourceUrl;

    @Column(nullable = false)
    private String permission;

    @Column(length = 9)
    private Long parentId;

    @Column(length = 100)
    private String parentIds;

    private Boolean isAvailable;

}
