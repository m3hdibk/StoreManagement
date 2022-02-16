package com.mehdi.storemanagement.util;

import java.util.Arrays;

public final class SchemeUtils {

    public enum UserType {
        CUSTOMER(1, "Customer"),
        SUPPLIER(2, "Supplier");


        private final int id;
        private final String name;

        UserType(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static UserType getById(int id) {
            return Arrays.stream(UserType.values())
                    .filter(userType -> id == userType.id)
                    .findFirst()
                    .orElse(null);
        }
    }

    public enum SchemeType {
        LOCATION(1, "Location"),
        CATEGORY(2, "Category"),
        BRAND(3, "Brand");


        private final int id;
        private final String name;

        SchemeType(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static SchemeType getById(int id) {
            return Arrays.stream(SchemeType.values())
                    .filter(schemeType -> id == schemeType.id)
                    .findFirst()
                    .orElse(null);
        }
        public static SchemeType getByName(String name) {
            return Arrays.stream(SchemeType.values())
                    .filter(schemeType -> name.equalsIgnoreCase(schemeType.name))
                    .findFirst()
                    .orElse(null);
        }
    }
}
