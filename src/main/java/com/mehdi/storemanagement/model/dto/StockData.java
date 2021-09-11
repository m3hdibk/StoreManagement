package com.mehdi.storemanagement.model.dto;


import com.mehdi.storemanagement.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StockData implements Serializable {


    @Serial
    private static final long serialVersionUID = -2731172984939460495L;

    private long id;

    private ProductData product;

    private SchemeData location;

    private int quantity;

    public Stock convertToEntity() {
        Stock stock = new Stock();
        stock.setId(id);
        stock.setProduct(product.convertToEntity());
        stock.setLocation(location.convertToEntity());
        stock.setQuantity(quantity);
        return stock;
    }
}
