package com.mehdi.storemanagement.util;

import com.mehdi.storemanagement.model.dto.*;
import com.mehdi.storemanagement.model.dto.response.*;

import java.util.List;
import java.util.stream.Collectors;

public final class ResponseConverterUtils {

    public static List<OrderResponse> convertToOrderResponse(List<OrderData> orderDataList) {
        return orderDataList.stream()
                .map(ResponseConverterUtils::getOrderResponse)
                .collect(Collectors.toList());
    }

    private static OrderInfoResponse getOrderResponse(OrderData orderData) {
        OrderInfoResponse orderResponse = new OrderInfoResponse();
        orderResponse.setId(orderData.getId());
        orderResponse.setRef(orderData.getRef());
        ClientData clientData = orderData.getClient();
        orderResponse.setClient(new SimpleValue<>(clientData.getId(),
                clientData.getLastName() != null ? clientData.getName() + " " + clientData.getLastName() :
                        clientData.getName()));
        orderResponse.setStatus(orderData.isStatus());
        orderResponse.setTotal(orderData.getTotalAmount());
        StockUtils.PaymentType paymentType = StockUtils.PaymentType.getById(orderData.getPaymentType());
        orderResponse.setPaymentType(new SimpleValue<>(paymentType.getId(), paymentType.getName()));
        return orderResponse;
    }

    public static OrderInfoResponse convertToOrderInfoResponse(OrderData orderData) {
        OrderInfoResponse orderInfoResponse = getOrderResponse(orderData);
        orderInfoResponse.setProducts(orderData.getProductOrder()
                .stream()
                .map(ResponseConverterUtils::convertToProductOrderResponse)
                .collect(Collectors.toList()));
        return orderInfoResponse;
    }

    private static TaxResponse convertToTaxResponse(TaxData taxData) {
        TaxResponse taxResponse = new TaxResponse();
        taxResponse.setId(taxData.getId());
        taxResponse.setAmount(taxData.getAmount());

        int type = taxData.getType();
        StockUtils.TaxType taxType = StockUtils.TaxType.getById(type);
        taxResponse.setType(new SimpleValue<>(taxType.getId(), taxType.getName()));
        if (type != 1) {
            StockUtils.TaxAmountType taxAmountType = StockUtils.TaxAmountType.getById(taxData.getAmountType());
            taxResponse.setAmountType(new SimpleValue<>(taxAmountType.getId(), taxAmountType.getName()));
        }
        taxResponse.setBeforeVAT(taxData.isBeforeVAT());
        return taxResponse;
    }

    public static List<TaxResponse> convertToTaxResponse(List<TaxData> taxDataList) {
        return taxDataList.stream().map(ResponseConverterUtils::convertToTaxResponse)
                .collect(Collectors.toList());
    }

    public static ProductOrderResponse convertToProductOrderResponse(ProductOrderData productOrderData) {
        ProductOrderResponse productOrderResponse = new ProductOrderResponse();
        productOrderResponse.setId(productOrderData.getId());
        productOrderResponse.setQuantity(productOrderData.getQuantity());
        productOrderResponse.setProduct(convertToProductResponse(productOrderData.getProduct()));
        return productOrderResponse;
    }

    public static ProductResponse convertToProductResponse(ProductData productData) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(productData.getId());
        productResponse.setProductCode(productData.getProductCode());
        productResponse.setBuyPrice(productData.getBuyPrice());
        productResponse.setSellPrice(productData.getSellPrice());
        productResponse.setUpc(productData.getUpc());
        productResponse.setTaxes(convertToTaxResponse(productData.getTaxes()));
        productResponse.setVat(convertToTaxResponse(productData.getVat()));
        StockUtils.Unit unit = StockUtils.Unit.getById(productData.getUnit());
        productResponse.setUnit(new SimpleValue<>(unit.getId(), unit.getName()));
        SchemeData brand = productData.getBrand();
        productResponse.setBrand(new SimpleValue<>(brand.getId(), brand.getName()));
        productResponse.setCategories(productData.getCategories().stream()
                .map(category -> new SimpleValue<>(category.getId(), category.getName()))
                .collect(Collectors.toSet()));
        productResponse.setPrice(CalcUtils.getProductFinalPrice(productData));
        return productResponse;
    }

    public static List<ProductResponse> convertToProductResponseList(List<ProductData> productResponseList) {
        return productResponseList.stream().map(ResponseConverterUtils::convertToProductResponse)
                .collect(Collectors.toList());
    }


}
