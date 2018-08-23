package com.account.domain.entity;

import com.account.domain.enums.JournalType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "account_journal")
public class Journal implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String journalName;

    @Enumerated(EnumType.STRING)
    private JournalType journalType;

    private String shortCode;

    private Long nextNumber;

    @OneToOne
    @JoinColumn(name = "default_debit_account_id", referencedColumnName = "id")
    private Account defaultDebitAccount;

    @OneToOne
    @JoinColumn(name = "default_credit_account_id", referencedColumnName = "id")
    private Account defaultCreditAccount;

    @JsonIgnore
    private Boolean isAvailable;


    @PrePersist
    private void prePersist() {
        this.nextNumber = 1L;
        this.isAvailable = true;
    }
}
