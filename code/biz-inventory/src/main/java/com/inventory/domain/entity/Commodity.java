package com.inventory.domain.entity;

import com.inventory.domain.enums.CommodityType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import javax.persistence.*;


@Data
@Entity
@Table(name = "inventory_commodity")
public class Commodity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CommodityType commodityType;

    private String quantityUnit;

    private Integer processingPeriod;

    private Boolean isAvailable;

    @PrePersist
    public void prePersist(){
        if(null == quantityUnit){
            this.quantityUnit = "one";
        }
        if(null == processingPeriod){
            processingPeriod = 0;
        }

        this.isAvailable = true;
    }

}
