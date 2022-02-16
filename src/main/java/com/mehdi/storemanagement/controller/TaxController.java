package com.mehdi.storemanagement.controller;

import com.mehdi.storemanagement.model.dto.response.TaxResponse;
import com.mehdi.storemanagement.service.impl.TaxServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/taxes")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class TaxController {

    private final TaxServiceImpl taxService;

    @GetMapping("/tax")
    public ResponseEntity<?> getAllTaxes() {

        List<TaxResponse> response = taxService.getAllTaxes();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/vat")
    public ResponseEntity<?> getAllVATs() {

        List<TaxResponse> response = taxService.getAllVATs();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
