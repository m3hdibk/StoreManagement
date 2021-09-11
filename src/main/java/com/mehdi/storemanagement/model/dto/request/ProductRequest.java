package com.mehdi.storemanagement.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 3257829579622376546L;

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
    private long brandId;
    private Set<Long> categoriesIds;
    private long locationId;
    private int quantity;

}
