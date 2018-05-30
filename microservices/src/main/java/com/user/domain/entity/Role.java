package com.user.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_role")
public class Role {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 9)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 90, unique = true)
    private String role;

    @Column(columnDefinition = "text")
    private String description;

    private Boolean isAvailable;
}
