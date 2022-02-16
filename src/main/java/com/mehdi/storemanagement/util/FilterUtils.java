package com.mehdi.storemanagement.util;


public final class FilterUtils {

    public enum DateType {
        Month(1, "Month"),
        Week(2, "Week");


        private final int id;
        private final String name;

        DateType(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public enum DashboardSumType {
        AMOUNT(1, "AMOUNT"),
        COUNT(2, "COUNT");


        private final int id;
        private final String name;

        DashboardSumType(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
