package com.mehdi.storemanagement.controller;


import com.mehdi.storemanagement.model.dto.request.StockUpdateRequest;
import com.mehdi.storemanagement.model.dto.response.PageResponse;
import com.mehdi.storemanagement.model.dto.response.StockLocationsResponse;
import com.mehdi.storemanagement.model.dto.response.StockSumQuantityResponse;
import com.mehdi.storemanagement.service.impl.StockServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class StockController {

    private final StockServiceImpl stockService;

    @GetMapping
    public ResponseEntity<?> getAllStocks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        PageResponse<StockSumQuantityResponse> response = stockService.getAllStockInformation(page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> SearchStockByProduct(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam String searchInput) {

        PageResponse<StockSumQuantityResponse> response = stockService.searchStockByProduct(searchInput, page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/locations")
    public ResponseEntity<?> getProductStockLocations(
            @RequestParam(name = "productId") long productId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        PageResponse<StockLocationsResponse> response = stockService.getStockLocations(productId, page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public void updateStock(@PathVariable long id,
                            @RequestBody StockUpdateRequest stockUpdateRequest) {
        stockService.updateStock(stockUpdateRequest, id);

    }
}
