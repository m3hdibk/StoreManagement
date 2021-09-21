package com.mehdi.storemanagement.model;

import com.mehdi.storemanagement.model.dto.SchemeData;
import com.mehdi.storemanagement.model.dto.StockData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity(name = "stock")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Stock implements Serializable {

    @Serial
    private static final long serialVersionUID = -6519071413696807906L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="scheme_id")
    private Scheme location;

    private int quantity;


    public StockData convertToData() {
        StockData stockData = new StockData();
        stockData.setId(id);
        stockData.setProduct(product.convertToData());
        stockData.setLocation(location.convertToData());
        stockData.setQuantity(quantity);
        return stockData;
    }
}
