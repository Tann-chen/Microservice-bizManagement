package com.tan.users.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "user_info")
public class User {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "gender")
    private String gender;

    @Column(name = "name")
    private String userName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "pwd_salt", nullable = false)
    private String pwdSalt;

    @Column(name = "created_time", nullable = false)
    private Timestamp createdTime;

    @Column(name = "last_login_time")
    private Timestamp lastLoginTime;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;


    @PrePersist
    public void prePersist(){
        this.createdTime = new Timestamp(System.currentTimeMillis());
    }
}
