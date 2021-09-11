package com.mehdi.storemanagement.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductUpdateRequest implements Serializable {


    @Serial
    private static final long serialVersionUID = 4508077729334837246L;

    private double buyPrice;

    private double sellPrice;
    private double length;
    private double width;
    private double height;
    private double weight;
    private long upc;
    private long brandId;
    private Set<Long> categoriesIds;
}
