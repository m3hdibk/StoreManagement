package com.mehdi.storemanagement.model.dto.response;

import com.mehdi.storemanagement.model.dto.SimpleValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockHistoryResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -2098442003876789088L;

    private long id;
    private SimpleValue<String> transactionType;
    private String comment;
    private int quantity;
    private LocalDate date;
    private LocalTime time;
}
