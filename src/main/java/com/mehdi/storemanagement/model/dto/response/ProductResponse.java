package com.mehdi.storemanagement.model.dto.response;


import com.mehdi.storemanagement.model.dto.SchemeData;
import com.mehdi.storemanagement.model.dto.SimpleValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 3140281477674304766L;


    private long id;
    private String productCode;

    private double buyPrice;

    private double sellPrice;
    private double length;
    private double width;
    private double height;
    private double weight;
    private long upc;

    private SimpleValue<String> brand;
    private Set<SimpleValue<String>> categories;

}
