package com.mehdi.storemanagement.model.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductOrderResponse implements Serializable {


    @Serial
    private static final long serialVersionUID = 8127577694170733778L;

    private long id;

    private ProductResponse product;

    private int quantity;
}
