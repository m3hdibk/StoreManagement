package com.mehdi.storemanagement.controller;

import com.mehdi.storemanagement.model.dto.request.ProductRequest;
import com.mehdi.storemanagement.model.dto.request.ProductUpdateRequest;
import com.mehdi.storemanagement.model.dto.response.PageResponse;
import com.mehdi.storemanagement.model.dto.ProductData;
import com.mehdi.storemanagement.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam String productCode) {

        PageResponse<ProductData> response = productService.getAllProducts(productCode, page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping
    public void createScheme(@Valid @RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateScheme(@PathVariable long id,
                                          @RequestBody ProductUpdateRequest productUpdateRequest) {
        productService.updateProduct(productUpdateRequest, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
