package com.mehdi.storemanagement.model.dto.response;

import com.mehdi.storemanagement.model.dto.SimpleValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockHistoryLocationResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 8206023307619422392L;

    private SimpleValue<String> location;
    private List<StockHistoryResponse> stockHistories;
}
