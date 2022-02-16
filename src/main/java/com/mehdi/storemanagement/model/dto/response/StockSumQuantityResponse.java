package com.mehdi.storemanagement.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockSumQuantityResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -7587874200558506308L;

    private long id;
    private ProductResponse product;
    private int sumQuantity;
}
