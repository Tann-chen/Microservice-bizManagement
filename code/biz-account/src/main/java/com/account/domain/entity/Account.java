package com.account.domain.entity;

import com.account.domain.enums.AccountType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "account_chart_of_accounts")
public class Account {
    @Id
    private Long code;

    private String name;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private Boolean allowReconciliation;

    private Boolean isDeprecated;

    private Boolean isAvailable;


    @PrePersist
    private void prePersist(){
        this.allowReconciliation = false;
        this.isDeprecated = false;
        this.isAvailable = true;
    }
}
