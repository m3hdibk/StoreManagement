package com.mehdi.storemanagement.model;

import com.mehdi.storemanagement.model.dto.StockHistoryData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "stockHistory")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StockHistory implements Serializable {

    @Serial
    private static final long serialVersionUID = 5213145380303342794L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "scheme_id")
    private Scheme location;

    private int quantity;

    private int transactionType;

    private String comment;
    private LocalDate date;
    private LocalTime time;


    public StockHistoryData convertToData() {
        StockHistoryData stockHistoryData = new StockHistoryData();
        stockHistoryData.setId(id);
        stockHistoryData.setStock(stock.convertToData());
        if (location != null) {
            stockHistoryData.setLocation(location.convertToData());
        }
        stockHistoryData.setQuantity(quantity);
        stockHistoryData.setTransactionType(transactionType);
        stockHistoryData.setComment(comment);
        stockHistoryData.setDate(date);
        stockHistoryData.setTime(time);
        return stockHistoryData;
    }
}

