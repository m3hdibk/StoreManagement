package com.mehdi.storemanagement.model.dto;

import com.mehdi.storemanagement.model.Stock;
import com.mehdi.storemanagement.model.StockHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StockHistoryData implements Serializable {

    @Serial
    private static final long serialVersionUID = -3503919374442658958L;
    private long id;
    private StockData stock;
    private SchemeData location;

    private int quantity;

    private int transactionType;
    private String comment;
    private LocalDate date;
    private LocalTime time;


    public StockHistory convertToEntity() {
        StockHistory stockHistory = new StockHistory();
        stockHistory.setId(id);
        stockHistory.setStock(stock.convertToEntity());
        stockHistory.setLocation(location.convertToEntity());
        stockHistory.setQuantity(quantity);
        stockHistory.setTransactionType(transactionType);
        stockHistory.setComment(comment);
        stockHistory.setDate(date);
        stockHistory.setTime(time);
        return stockHistory;
    }
}
