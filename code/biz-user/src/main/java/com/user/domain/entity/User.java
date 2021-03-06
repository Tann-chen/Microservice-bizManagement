package com.user.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.user.domain.enums.JobStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "user_info")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String avatar;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonIgnore
    private String pwdSalt;

    @Transient
    @JsonIgnore
    private List<String> roleStrLst;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_relation_info_role",
            joinColumns = {@JoinColumn(referencedColumnName = "id", name = "user_id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id", name = "user_role_id")})
    private List<Role> roleList;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_relation_info_permission",
            joinColumns = {@JoinColumn(referencedColumnName = "id", name = "user_id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id", name = "user_permission_id")})
    private List<Permission> permissionList;

    @JsonIgnore
    private Timestamp createTime;

    @JsonIgnore
    private Timestamp lastLoginTime;

    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;

    @JsonIgnore
    private Boolean isAvailable;

    @PrePersist
    public void prePersist() {
        if (this.email.length() >= 10) {
            this.pwdSalt = this.email.substring(5, 8);
        } else {
            this.pwdSalt = this.email.substring(0, 3);
        }
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.jobStatus = JobStatus.UNDISTRIBUTED;
        this.isAvailable = true;
    }
}
