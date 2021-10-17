package com.mehdi.storemanagement.util;

import com.mehdi.storemanagement.model.dto.SimpleValue;
import com.mehdi.storemanagement.model.dto.StockHistoryData;
import com.mehdi.storemanagement.model.dto.response.StockHistoryResponse;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class StockUtils {

    public enum StockTransactionType {
        SELL(1, "Sell"),
        BUY(2, "Buy"),
        TRANSFER(3, "Transfer");


        private final int id;
        private final String name;

        StockTransactionType(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static StockTransactionType getById(int id) {
            return Arrays.stream(StockTransactionType.values())
                    .filter(schemeType -> id == schemeType.id)
                    .findFirst()
                    .orElse(null);
        }
        public static StockTransactionType getByName(String name) {
            return Arrays.stream(StockTransactionType.values())
                    .filter(schemeType -> name.equalsIgnoreCase(schemeType.name))
                    .findFirst()
                    .orElse(null);
        }
    }

    public static List<StockHistoryResponse> getStockHistoryResponseList(List<StockHistoryData> stockHistoryDataList) {
        return stockHistoryDataList.stream().map(stockHistoryData -> {
            StockHistoryResponse stockHistoryResponse = new StockHistoryResponse();
            stockHistoryResponse.setId(stockHistoryData.getId());
            stockHistoryResponse.setTransactionType(new SimpleValue<>(stockHistoryData.getTransactionType(),
                    StockTransactionType.getById(stockHistoryData.getTransactionType()).getName()));
            stockHistoryResponse.setComment(stockHistoryData.getComment());
            stockHistoryResponse.setQuantity(stockHistoryData.getQuantity());
            stockHistoryResponse.setDate(stockHistoryData.getDate());
            stockHistoryResponse.setTime(stockHistoryData.getTime());
            return stockHistoryResponse;
        }).collect(Collectors.toList());
    }

    public enum PaymentType {
        CASH(1, "Cash"),
        CREDIT(2, "Credit");


        private final int id;
        private final String name;

        PaymentType(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static PaymentType getById(int id) {
            return Arrays.stream(PaymentType.values())
                    .filter(paymentType -> id == paymentType.id)
                    .findFirst()
                    .orElse(null);
        }
        public static PaymentType getByName(String name) {
            return Arrays.stream(PaymentType.values())
                    .filter(paymentType -> name.equalsIgnoreCase(paymentType.name))
                    .findFirst()
                    .orElse(null);
        }
    }

    public enum TaxType {
        VAT(1, "VAT"),
        TAX(2, "Tax");


        private final int id;
        private final String name;

        TaxType(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static TaxType getById(int id) {
            return Arrays.stream(TaxType.values())
                    .filter(taxType -> id == taxType.id)
                    .findFirst()
                    .orElse(null);
        }
        public static TaxType getByName(String name) {
            return Arrays.stream(TaxType.values())
                    .filter(taxType -> name.equalsIgnoreCase(taxType.name))
                    .findFirst()
                    .orElse(null);
        }
    }

    public enum TaxAmountType {
        FIXED(1, "Fixed"),
        Percent(2, "Percent");


        private final int id;
        private final String name;

        TaxAmountType(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static TaxAmountType getById(int id) {
            return Arrays.stream(TaxAmountType.values())
                    .filter(taxAmountType -> id == taxAmountType.id)
                    .findFirst()
                    .orElse(null);
        }
        public static TaxAmountType getByName(String name) {
            return Arrays.stream(TaxAmountType.values())
                    .filter(taxAmountType -> name.equalsIgnoreCase(taxAmountType.name))
                    .findFirst()
                    .orElse(null);
        }
    }

    public enum PriceMethod {
        PUBLIC(1, "Public Price"),
        FIX(2, "Fixed Amount");


        private final int id;
        private final String name;

        PriceMethod(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static PriceMethod getById(int id) {
            return Arrays.stream(PriceMethod.values())
                    .filter(priceMethod -> id == priceMethod.id)
                    .findFirst()
                    .orElse(null);
        }
        public static PriceMethod getByName(String name) {
            return Arrays.stream(PriceMethod.values())
                    .filter(priceMethod -> name.equalsIgnoreCase(priceMethod.name))
                    .findFirst()
                    .orElse(null);
        }
    }

    public enum PriceRule {
        FIX(1, "Fix"),
        PERCENT(2, "Percent");


        private final int id;
        private final String name;

        PriceRule(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static PriceRule getById(int id) {
            return Arrays.stream(PriceRule.values())
                    .filter(priceRule -> id == priceRule.id)
                    .findFirst()
                    .orElse(null);
        }
        public static PriceRule getByName(String name) {
            return Arrays.stream(PriceRule.values())
                    .filter(priceRule -> name.equalsIgnoreCase(priceRule.name))
                    .findFirst()
                    .orElse(null);
        }
    }

    public enum Unit {
        PIECE(1, "Piece"),
        ML(2, "Ml"),
        CL(3, "Cl"),
        L(4, "L"),
        GR(5, "Gr"),
        KG(6, "Kg"),
        TONNE(7, "Tonne"),
        M2(8, "M2"),
        M3(9, "M3");


        private final int id;
        private final String name;

        Unit(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static Unit getById(int id) {
            return Arrays.stream(Unit.values())
                    .filter(unit -> id == unit.id)
                    .findFirst()
                    .orElse(null);
        }
        public static Unit getByName(String name) {
            return Arrays.stream(Unit.values())
                    .filter(unit -> name.equalsIgnoreCase(unit.name))
                    .findFirst()
                    .orElse(null);
        }
    }
}
