package com.mehdi.storemanagement.model.dto.response;



import com.mehdi.storemanagement.model.dto.SimpleValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -1859051845441827548L;

    private long id;

    private ProductResponse product;

    private SimpleValue<String> location;

    private int quantity;
}
