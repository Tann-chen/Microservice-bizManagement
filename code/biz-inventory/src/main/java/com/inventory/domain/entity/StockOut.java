package com.inventory.domain.entity;

import com.inventory.domain.enums.StockOutPurpose;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "inventory_stock_out")
public class StockOut implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    private Timestamp pickedTime;

    private Long pickedUser;

    private Long approvedUser;

    @Enumerated(EnumType.STRING)
    private StockOutPurpose purpose;

    private String note;

    @PrePersist
    public void prePersist() {
        if (null == pickedTime) {
            this.pickedTime = new Timestamp(System.currentTimeMillis());
        }
    }
}
