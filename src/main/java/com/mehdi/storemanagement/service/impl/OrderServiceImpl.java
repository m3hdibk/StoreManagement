package com.mehdi.storemanagement.service.impl;

import com.mehdi.storemanagement.model.*;
import com.mehdi.storemanagement.model.dto.ProductData;
import com.mehdi.storemanagement.model.dto.StockData;
import com.mehdi.storemanagement.model.dto.StockHistoryData;
import com.mehdi.storemanagement.model.dto.request.OrderRequest;
import com.mehdi.storemanagement.model.dto.request.ProductOrderRequest;
import com.mehdi.storemanagement.repository.*;
import com.mehdi.storemanagement.service.OrderService;
import com.mehdi.storemanagement.util.StockUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public record OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository,
                               StockRepository stockRepository, StockHistoryRepository stockHistoryRepository,
                               ProductOrderRepository productOrderRepository)
        implements OrderService {

    @Override
    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        List<ProductOrder> productOrderList = new ArrayList<>();
        if (orderRequest.getClientId() != 0) {
            System.out.print("client");
        }
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        double totalAmount = 0;
        for (ProductOrderRequest productOrder : orderRequest.getProducts()) {
            Optional<Stock> optionalStock = stockRepository.findById(productOrder.getStockId());
            if (optionalStock.isEmpty()) {
                throw new EntityNotFoundException("Stock not found, StockId : " + productOrder.getStockId());
            }
            StockData stockData = optionalStock.get().convertToData();
            //TODO quantity check
            totalAmount += stockData.getProduct().getSellPrice() * productOrder.getQuantity();
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

        order.setStatus(true);
        order.setPaymentType(orderRequest.getPaymentType());
        order.setDiscount(orderRequest.getDiscount());
        order.setAmount(totalAmount);
        order.setAmount(totalAmount - orderRequest.getDiscount());
        order.setProductOrder(productOrderList);
        orderRepository.save(order);
    }
}
