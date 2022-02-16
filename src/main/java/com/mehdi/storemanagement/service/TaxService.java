package com.mehdi.storemanagement.service;

import com.mehdi.storemanagement.model.dto.response.TaxResponse;

import java.util.List;

public interface TaxService {

    List<TaxResponse> getAllTaxes();
    List<TaxResponse> getAllVATs();
}
