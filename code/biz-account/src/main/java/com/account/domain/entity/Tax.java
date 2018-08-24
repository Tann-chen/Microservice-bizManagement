package com.account.domain.entity;

import com.account.domain.enums.TaxComputation;
import com.account.domain.enums.TaxScope;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Entity
@Data
@Table(name = "account_taxes")
public class Tax {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taxName;

    @Enumerated(EnumType.STRING)
    private TaxScope taxScope;

    @Enumerated(EnumType.STRING)
    private TaxComputation taxComputation;

    private Double amount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_account_id", referencedColumnName = "id")
    private Account taxAccount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_account_on_credit_notes_id", referencedColumnName = "id")
    private Account taxAccountOnCreditNotes;

    private String labelOnInvoices;

    private Boolean isActive;

    @JsonIgnore
    private Boolean isAvailable;


    @PrePersist
    private void prePersist() {
        this.isActive = true;
        this.isAvailable = true;
    }
}


