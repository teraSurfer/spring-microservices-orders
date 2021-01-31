package com.shopapp.orders.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
public class OrderItem implements Serializable {

    private static final long serialVersionUID = -3411442759304067334L;

    private long productId;
    private int productCount;
}
