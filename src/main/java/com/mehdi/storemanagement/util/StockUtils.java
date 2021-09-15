package com.mehdi.storemanagement.util;

import java.util.Arrays;

public class StockUtils {

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
}
