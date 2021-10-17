package com.mehdi.storemanagement.model;

import com.mehdi.storemanagement.model.dto.ProductData;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="taxes", joinColumns=@JoinColumn(name="sale_id"),
            inverseJoinColumns=@JoinColumn(name="tax_id"))
    private List<Tax> taxes;

    @ManyToOne
    private Tax vat;
    private int unit;
    private String upc;

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
        if (taxes != null) {
            productData.setTaxes(taxes.stream().map(Tax::convertToData).collect(Collectors.toList()));
        }
        productData.setVat(vat.convertToData());
        productData.setUnit(unit);
        productData.setUpc(upc);
        productData.setBrand(brand.convertToData());
        if (categories != null) {
            productData.setCategories(categories.stream().map(Scheme::convertToData).collect(Collectors.toSet()));
        }
        return productData;
    }

}
