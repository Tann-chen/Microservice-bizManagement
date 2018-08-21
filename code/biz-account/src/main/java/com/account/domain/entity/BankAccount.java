package com.account.domain.entity;

import com.account.domain.enums.Method;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "account_bank_account")
public class BankAccount implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String accountNumber;

    private String bank;

    @Enumerated(EnumType.STRING)
    private Method debitMethod;

    @Enumerated(EnumType.STRING)
    private Method paymentMethod;

    @JsonIgnore
    private Boolean isAvailable;


    @PrePersist
    private void prePersist(){
        this.isAvailable = true;
    }

}
