package com.mehdi.storemanagement.model.dto.response;


import com.mehdi.storemanagement.model.dto.ProductData;
import com.mehdi.storemanagement.model.dto.SchemeData;
import com.mehdi.storemanagement.model.dto.SimpleValue;
import com.mehdi.storemanagement.model.dto.StockHistoryData;
import com.mehdi.storemanagement.util.StockUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockHistoryInformationResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -2098442003876789088L;

    private ProductData product;

    private List<StockHistoryLocationResponse> stockHistoryPerLocation;

    public void build(List<StockHistoryData> stockHistoryDataList) {
        if (stockHistoryDataList.isEmpty()) {
            return;
        }
        product = stockHistoryDataList.get(0).getStock().getProduct();
        Map<SchemeData , List<StockHistoryData>> groupByLocation = stockHistoryDataList.stream()
                .collect(Collectors.groupingBy(stockHistoryData -> stockHistoryData.getStock().getLocation()));
        stockHistoryPerLocation = groupByLocation.entrySet().stream().map(schemeDataListEntry -> {
            StockHistoryLocationResponse stockHistoryLocationResponse = new StockHistoryLocationResponse();
            SchemeData schemeData = schemeDataListEntry.getKey();
            stockHistoryLocationResponse.setLocation(new SimpleValue<>(schemeData.getId(), schemeData.getName()));
            stockHistoryLocationResponse.setStockHistories(schemeDataListEntry.getValue().stream().map(stockHistoryData -> {
                StockHistoryResponse stockHistoryResponse = new StockHistoryResponse();
                stockHistoryResponse.setId(stockHistoryData.getId());
                stockHistoryResponse.setTransactionType(new SimpleValue<>(stockHistoryData.getTransactionType(),
                        StockUtils.StockTransactionType.getById(stockHistoryData.getTransactionType()).getName()));
                stockHistoryResponse.setComment(stockHistoryData.getComment());
                stockHistoryResponse.setQuantity(stockHistoryData.getQuantity());
                stockHistoryResponse.setDate(stockHistoryData.getDate());
                stockHistoryResponse.setTime(stockHistoryData.getTime());
                return stockHistoryResponse;
            } ).collect(Collectors.toList()));
            return stockHistoryLocationResponse;
        }).collect(Collectors.toList());
    }
}
