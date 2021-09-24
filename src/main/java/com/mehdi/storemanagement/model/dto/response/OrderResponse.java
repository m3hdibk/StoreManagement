package com.mehdi.storemanagement.model.dto.response;

import com.mehdi.storemanagement.model.dto.ClientData;
import com.mehdi.storemanagement.model.dto.SimpleValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -3075005950811703020L;

    private long id;
    private String ref;
    private SimpleValue<String> client;
    private double total;
    private SimpleValue<String> paymentType;
    private boolean status;
}
