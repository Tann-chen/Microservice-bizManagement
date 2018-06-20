package com.user.domain.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
public class Module implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Boolean isAvailable;

    @PrePersist
    public void prePersist() {
        if (null == this.isAvailable) {
            this.isAvailable = false;
        }
    }
}
