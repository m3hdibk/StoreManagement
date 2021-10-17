package com.mehdi.storemanagement.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mehdi.storemanagement.model.Product;
import com.mehdi.storemanagement.model.Tax;
import com.mehdi.storemanagement.model.dto.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
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

    private List<TaxData> taxes;

    private TaxData vat;
    private int unit;
    private String upc;


    private SchemeData brand;
    private Set<SchemeData> categories;


    public Product convertToEntity() {
        Product product = new Product();
        product.setId(id);
        product.setProductCode(productCode);
        product.setBuyPrice(buyPrice);
        product.setSellPrice(sellPrice);
        product.setTaxes(taxes.stream().map(TaxData::convertToEntity).collect(Collectors.toList()));
        product.setVat(vat.convertToEntity());
        product.setUnit(unit);
        product.setUpc(upc);
        product.setBrand(brand.convertToEntity());
        product.setCategories(categories.stream().map(SchemeData::convertToEntity).collect(Collectors.toSet()));
        return product;
    }
}
