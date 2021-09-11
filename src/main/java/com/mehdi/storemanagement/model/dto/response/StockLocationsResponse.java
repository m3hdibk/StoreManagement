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
public class StockLocationsResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 5702391214585263021L;

    private SimpleValue<String> location;
    private int quantity;
}
