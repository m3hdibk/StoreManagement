package com.mehdi.storemanagement.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockUpdateRequest implements Serializable {


    @Serial
    private static final long serialVersionUID = -5033322571440031948L;

    private int transactionType;
    private int quantity;
    private String comment;
    private long newLocation;

}
