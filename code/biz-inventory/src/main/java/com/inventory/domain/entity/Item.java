package com.inventory.domain.entity;

import com.inventory.domain.enums.ItemStatus;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "inventory_item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serialId;

    private String SkuNo;

    @ManyToOne
    @JoinColumn(name = "commodity_id", referencedColumnName = "id")
    private Commodity commodity;

    private Long StockInId;

    @Enumerated(EnumType.STRING)
    private ItemStatus commodityStatus;

    private Double costPerItem;

    private Boolean isAvailable;

    @PrePersist
    public void prePersist(){
        if(null == costPerItem){
            this.costPerItem = 0.0;
        }

        this.isAvailable = true;
    }
}
