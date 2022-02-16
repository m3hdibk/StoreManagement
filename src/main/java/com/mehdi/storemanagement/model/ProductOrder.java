package com.mehdi.storemanagement.model;

import com.mehdi.storemanagement.model.dto.ProductOrderData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity(name = "productOrder")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductOrder implements Serializable {


    @Serial
    private static final long serialVersionUID = 5697940514025383003L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Product product;

    private int quantity;
    private double discount;

    public ProductOrderData convertToData() {
        ProductOrderData productOrder = new ProductOrderData();
        productOrder.setId(id);
        productOrder.setProduct(product.convertToData());
        productOrder.setQuantity(quantity);
        productOrder.setDiscount(discount);
        return productOrder;
    }

}
