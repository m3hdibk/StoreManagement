package com.mehdi.storemanagement.controller;



import com.mehdi.storemanagement.model.dto.request.OrderRequest;
import com.mehdi.storemanagement.model.dto.response.OrderInfoResponse;
import com.mehdi.storemanagement.model.dto.response.OrderResponse;
import com.mehdi.storemanagement.model.dto.response.PageResponse;
import com.mehdi.storemanagement.service.impl.OrderServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class OrderController {

    private final OrderServiceImpl orderServiceImpl;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {

        orderServiceImpl.createOrder(orderRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "date") String sort) {

        PageResponse<OrderResponse> response = orderServiceImpl.getAllOrders(page, size, sort);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderInfoResponse> getOrderInfo(@PathVariable long id) {
        OrderInfoResponse orderInfoResponse = orderServiceImpl.getOrderInfo(id);
        return new ResponseEntity<>(orderInfoResponse, HttpStatus.OK);
    }
}
