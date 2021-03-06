package com.mehdi.storemanagement.service.impl;

import com.mehdi.storemanagement.exception.NoEnoughQuantityException;
import com.mehdi.storemanagement.model.*;
import com.mehdi.storemanagement.model.dto.OrderData;
import com.mehdi.storemanagement.model.dto.ProductData;
import com.mehdi.storemanagement.model.dto.StockData;
import com.mehdi.storemanagement.model.dto.StockHistoryData;
import com.mehdi.storemanagement.model.dto.request.OrderRequest;
import com.mehdi.storemanagement.model.dto.request.ProductOrderRequest;
import com.mehdi.storemanagement.model.dto.response.OrderInfoResponse;
import com.mehdi.storemanagement.model.dto.response.OrderResponse;
import com.mehdi.storemanagement.model.dto.response.PageResponse;
import com.mehdi.storemanagement.repository.*;
import com.mehdi.storemanagement.service.OrderService;
import com.mehdi.storemanagement.util.ResponseConverterUtils;
import com.mehdi.storemanagement.util.StockUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public record OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository,
                               StockRepository stockRepository, StockHistoryRepository stockHistoryRepository,
                               ProductOrderRepository productOrderRepository)
        implements OrderService {

    @Override
    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        List<ProductOrder> productOrderList = new ArrayList<>();
        User user = new User();
        user.setId(orderRequest.getClientId());
        order.setUser(user);
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        double totalAmount = 0;
        for (ProductOrderRequest productOrder : orderRequest.getProducts()) {
            Optional<Stock> optionalStock = stockRepository.findById(productOrder.getStockId());
            if (optionalStock.isEmpty()) {
                throw new EntityNotFoundException("Stock not found, StockId : " + productOrder.getStockId());
            }
            StockData stockData = optionalStock.get().convertToData();
            if (stockData.getQuantity() < productOrder.getQuantity()) {
                throw new NoEnoughQuantityException("No enough quantity in the Stock");
            }
            ProductData productData = stockData.getProduct();
            totalAmount += ((productData.getSellPrice() - productOrder.getDiscount()) * (productData.getVat().getAmount() / 100 + 1)) *
                    productOrder.getQuantity();
            stockData.setQuantity(stockData.getQuantity() - productOrder.getQuantity());
            StockHistoryData stockHistory = new StockHistoryData();
            stockHistory.setStock(stockData);
            stockHistory.setQuantity(productOrder.getQuantity());
            stockHistory.setTransactionType(StockUtils.StockTransactionType.SELL.getId());
            stockHistory.setTime(currentTime);
            stockHistory.setDate(currentDate);
            stockRepository.save(stockData.convertToEntity());
            stockHistoryRepository.save(stockHistory.convertToEntity());
            ProductOrder productOrderEntity = new ProductOrder();
            productOrderEntity.setProduct(stockData.convertToEntity().getProduct());
            productOrderEntity.setQuantity(productOrder.getQuantity());
            productOrderList.add(productOrderEntity);
            productOrderRepository.save(productOrderEntity);
        }

        order.setStatus(orderRequest.isPaymentStatus());
        order.setPaymentType(orderRequest.getPaymentType());
        order.setDiscount(orderRequest.getDiscount());
        order.setAmount(totalAmount);
        order.setTotalAmount(totalAmount - orderRequest.getDiscount());
        order.setProductOrder(productOrderList);
        order.setDate(LocalDateTime.of(currentDate, currentTime));
        orderRepository.save(order);
    }

    @Override
    public PageResponse<OrderResponse> getAllOrders(int page, int size, String sort) {
        Pageable paging = PageRequest.of(page, size).withSort(Sort.by(sort));
        Page<Order> pageOrder = orderRepository.findAll(paging);

        List<OrderData> orders = pageOrder.getContent().stream()
                .map(Order::convertToData)
                .collect(Collectors.toList());

        return new PageResponse<>(ResponseConverterUtils.convertToOrderResponse(orders), pageOrder.getNumber(),
                pageOrder.getTotalElements(),
                pageOrder.getTotalPages());
    }

    @Override
    public OrderInfoResponse getOrderInfo(long id) {
        Optional<Order> optOrder = orderRepository.findById(id);
        if (optOrder.isEmpty()) {
            return null;
        }
        OrderData order = optOrder.get().convertToData();
        return ResponseConverterUtils.convertToOrderInfoResponse(order);

    }
}
