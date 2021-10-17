package com.mehdi.storemanagement.model;

import com.mehdi.storemanagement.model.dto.TaxData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;

@Entity(name = "tax")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tax implements Serializable {


    @Serial
    private static final long serialVersionUID = -516258272728309152L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int type;
    private int amountType;
    private double amount;
    private boolean beforeVAT;

    public TaxData convertToData() {
        TaxData taxData = new TaxData();
        taxData.setId(id);
        taxData.setType(type);
        taxData.setAmountType(amountType);
        taxData.setAmount(amount);
        taxData.setBeforeVAT(beforeVAT);
        return taxData;
    }
}
