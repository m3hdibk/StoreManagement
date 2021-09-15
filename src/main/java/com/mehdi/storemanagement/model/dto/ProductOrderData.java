package com.mehdi.storemanagement.model.dto;

import com.mehdi.storemanagement.model.ProductOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductOrderData implements Serializable {

    @Serial
    private static final long serialVersionUID = -8543593595082119034L;

    private long id;

    private ProductData product;

    private int quantity;

    public ProductOrder convertToEntity() {
        ProductOrder productOrder = new ProductOrder();
        productOrder.setId(id);
        productOrder.setProduct(product.convertToEntity());
        productOrder.setQuantity(quantity);
        return productOrder;
    }
}
