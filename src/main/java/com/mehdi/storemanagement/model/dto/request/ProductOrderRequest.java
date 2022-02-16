package com.mehdi.storemanagement.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductOrderRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -4539978812355213230L;

    private long stockId;
    private int quantity;
    private double discount;
}
