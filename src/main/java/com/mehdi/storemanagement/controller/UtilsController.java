package com.mehdi.storemanagement.controller;


import com.mehdi.storemanagement.model.dto.SimpleValue;
import com.mehdi.storemanagement.model.dto.response.TaxResponse;
import com.mehdi.storemanagement.util.StockUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/utils")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UtilsController {

    @GetMapping("/unit")
    public ResponseEntity<?> getAllUnits() {

        List<SimpleValue<String>> response = Arrays.stream(StockUtils.Unit.values())
                .map(unit -> new SimpleValue<>(unit.getId(), unit.getName()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
