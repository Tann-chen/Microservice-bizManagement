package com.inventory.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventory.domain.enums.ItemStatus;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "inventory_item")
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "serial_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serialId;

    private String skuNo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "commodity_id", referencedColumnName = "id")
    private Commodity commodityId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_in_id", referencedColumnName = "id")
    private StockIn stockInId;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    private Double costPerItem;

    @JsonIgnore
    private Boolean isAvailable;

    @PrePersist
    public void prePersist() {
        if (null == costPerItem) {
            this.costPerItem = 0.0;
        }

        this.isAvailable = true;
    }
}
