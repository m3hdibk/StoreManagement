package com.mehdi.storemanagement.model;

import com.mehdi.storemanagement.model.dto.ProductData;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "product")
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueTypeAndName",
        columnNames = { "productCode", "upc" }) })
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = -7019253637795132559L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String productCode;
    private double buyPrice;
    private double sellPrice;
    private double length;
    private double width;
    private double height;
    private double weight;
    private long upc;

    @ManyToOne
    @JoinColumn(name="scheme_id")
    private Scheme brand;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="category", joinColumns=@JoinColumn(name="product_id"),
            inverseJoinColumns=@JoinColumn(name="scheme_id"))
    private Set<Scheme> categories;




    public ProductData convertToData() {
        ProductData productData = new ProductData();
        productData.setId(id);
        productData.setProductCode(productCode);
        productData.setBuyPrice(buyPrice);
        productData.setSellPrice(sellPrice);
        productData.setLength(length);
        productData.setWidth(width);
        productData.setWeight(weight);
        productData.setUpc(upc);
        productData.setBrand(brand.convertToData());
        productData.setCategories(categories.stream().map(Scheme::convertToData).collect(Collectors.toSet()));
        return productData;
    }

}
