package com.shopapp.orders.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Setter
@Getter
public class Orders implements Serializable {

    private static final long serialVersionUID = 4710238698017113009L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name= "total_price")
    private double totalPrice;

    @Column()
    private String status;

    @Column(name="shipping_address")
    private String shippingAddress;

    @Column(name="created_at")
    private Timestamp createdAt;


    @Column(name="updated_at")
    private Timestamp updatedAt;

    @PrePersist
    public void beforeCreate() {
        long now = Calendar.getInstance().getTimeInMillis();
        this.createdAt = new Timestamp(now);
        this.updatedAt = new Timestamp(now);
    }

    @PreUpdate
    public void beforeUpdate() {
        this.updatedAt = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
