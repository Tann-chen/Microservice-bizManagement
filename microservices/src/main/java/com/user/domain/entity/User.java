package com.user.domain.entity;


import com.user.domain.enums.JobStatus;
import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "user_info")
public class User {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 9)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45)
    private String name;

    @Column(length = 45, unique = true)
    private String email;

    @Column(length = 45)
    private String phone;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private Blob avatar;

    private String password;

    @Column(length = 30)
    private String pwdSalt;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp lastLoginTime;

    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;

    private Boolean isActive;
}
