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
public class TaxResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 4122482150179847308L;

    private long id;
    private SimpleValue<String> type;
    private SimpleValue<String> amountType;
    private double amount;
    private boolean beforeVAT;

}
