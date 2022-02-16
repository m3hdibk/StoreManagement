package com.mehdi.storemanagement.service.impl;

import com.mehdi.storemanagement.model.Tax;
import com.mehdi.storemanagement.model.dto.response.TaxResponse;
import com.mehdi.storemanagement.repository.TaxRepository;
import com.mehdi.storemanagement.service.TaxService;
import com.mehdi.storemanagement.util.ResponseConverterUtils;
import com.mehdi.storemanagement.util.StockUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public record TaxServiceImpl(TaxRepository taxRepository) implements TaxService {


    @Override
    public List<TaxResponse> getAllTaxes() {
        return getTaxResponseList(StockUtils.TaxType.TAX.getId());
    }

    @Override
    public List<TaxResponse> getAllVATs() {
        return getTaxResponseList(StockUtils.TaxType.VAT.getId());
    }

    private List<TaxResponse> getTaxResponseList(int taxType) {
        List<Tax> taxList = taxRepository.findByType(taxType);
        return taxList.stream().map(Tax::convertToData)
                .map(ResponseConverterUtils::convertToTaxResponse)
                .collect(Collectors.toList());
    }
}
