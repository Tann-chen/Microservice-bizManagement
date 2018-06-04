package com.user.domain.entity;


import com.user.domain.enums.JobStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "user_info")
public class User implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 9)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    private String name;

    @Column(length = 45, unique = true)
    private String email;

    @Column(length = 45)
    private String phone;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private Blob avatar;

    @Basic
    private String password;

    @Column(length = 30)
    private String pwdSalt;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "user_relation_info_role",
            joinColumns = {@JoinColumn(referencedColumnName = "id", name = "user_id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id", name = "user_role_id")})
    private List<Role> roleList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_relation_info_permission",
            joinColumns = {@JoinColumn(referencedColumnName = "id", name = "user_id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id", name = "user_permission_id")})
    private List<Permission> permissionList;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp lastLoginTime;

    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;

    @Basic
    private Boolean isActive;


    @Override
    public Object clone() {
        User copy = null;
        try {
            copy = (User) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return copy;
    }
}
