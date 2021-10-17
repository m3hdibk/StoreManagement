package com.mehdi.storemanagement.model.dto;

import com.mehdi.storemanagement.model.StockHistory;
import com.mehdi.storemanagement.model.Tax;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaxData implements Serializable {

    @Serial
    private static final long serialVersionUID = -5061325372896336651L;

    private long id;
    private int type;
    private int amountType;
    private double amount;
    private boolean beforeVAT;

    public Tax convertToEntity() {
        Tax tax = new Tax();
        tax.setId(id);
        tax.setType(type);
        tax.setAmountType(amountType);
        tax.setAmount(amount);
        tax.setBeforeVAT(beforeVAT);
        return tax;
    }
}
