package com.mehdi.storemanagement.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 4648149427194952428L;

    private long clientId;
    private List<ProductOrderRequest> products;
    private double discount;
    private boolean paymentStatus;
    private int paymentType;
}
