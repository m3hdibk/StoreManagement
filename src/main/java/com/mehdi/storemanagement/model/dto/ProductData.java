package com.mehdi.storemanagement.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mehdi.storemanagement.model.Product;
import com.mehdi.storemanagement.model.Scheme;
import com.mehdi.storemanagement.model.dto.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductData implements Serializable {

    @Serial
    private static final long serialVersionUID = 3537968934621792655L;

    private long id;

    @JsonProperty(required = true)
    @NotBlank(message = "productCode must have a value")
    @NotNull
    private String productCode;

    private double buyPrice;

    private double sellPrice;
    private double length;
    private double width;
    private double height;
    private double weight;
    private long upc;


    private SchemeData brand;
    private Set<SchemeData> categories;


    public Product convertToEntity() {
        Product product = new Product();
        product.setId(id);
        product.setProductCode(productCode);
        product.setBuyPrice(buyPrice);
        product.setSellPrice(sellPrice);
        product.setLength(length);
        product.setWidth(width);
        product.setWeight(weight);
        product.setUpc(upc);
        product.setBrand(brand.convertToEntity());
        product.setCategories(categories.stream().map(SchemeData::convertToEntity).collect(Collectors.toSet()));
        return product;
    }

    public ProductResponse convertToResponse() {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(id);
        productResponse.setProductCode(productCode);
        productResponse.setBuyPrice(buyPrice);
        productResponse.setSellPrice(sellPrice);
        productResponse.setLength(length);
        productResponse.setWidth(width);
        productResponse.setWeight(weight);
        productResponse.setUpc(upc);
        productResponse.setBrand(new SimpleValue<>(brand.getId(), brand.getName()));
        productResponse.setCategories(categories.stream()
                .map(category -> new SimpleValue<>(category.getId(), category.getName()))
                .collect(Collectors.toSet()));
        return productResponse;
    }
}
