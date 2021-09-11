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
}
