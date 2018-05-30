package com.user.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 90, unique = true)
    private String name;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private String resourceType;

    @Column(length = 150, nullable = false)
    private String resourceUrl;

    @Column(nullable = false)
    private String permission;

    private Integer parentId;

    private String parentIds;

    private Boolean isAvailable;
}
