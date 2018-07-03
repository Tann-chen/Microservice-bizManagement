package com.inventory.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "inventory_stock_in")
public class StockIn implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String batchNo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "commodity_id", referencedColumnName = "id")
    private Commodity commodityId;

    private Timestamp entryTime;

    private Long receiveUserId;

    private String note;

    @PrePersist
    public void prePersist() {
        if (null == entryTime) {
            this.entryTime = new Timestamp(System.currentTimeMillis());
        }
    }
}
