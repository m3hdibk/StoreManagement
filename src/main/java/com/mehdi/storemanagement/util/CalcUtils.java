package com.mehdi.storemanagement.util;

import com.mehdi.storemanagement.model.dto.ProductData;
import com.mehdi.storemanagement.model.dto.TaxData;

import java.util.ArrayList;
import java.util.List;

public final class CalcUtils {


    public static double getProductFinalPrice(ProductData productData) {

        double sellPrice = productData.getSellPrice();
        double finalPrice = sellPrice;
        boolean applyTaxes = productData.getTaxes() != null;
        boolean applyVats = productData.getVat() != null;
        List<TaxData> taxes = productData.getTaxes();
        List<TaxData> taxesAfterVat = new ArrayList<>();

        // step 1 add taxes before vat
        if (applyTaxes) {
            for (TaxData taxData : taxes) {
                if (taxData.isBeforeVAT()) {
                    if (StockUtils.TaxAmountType.FIXED.getId() == taxData.getType()) {
                        finalPrice += taxData.getAmount();
                    } else {
                        finalPrice += taxData.getAmount() * sellPrice;
                    }
                } else {
                    taxesAfterVat.add(taxData);
                }
            }
        }

        // step 2 add VAT
        if (applyVats) {
            finalPrice *= productData.getVat().getAmount() /100 + 1;
        }

        // step 3 add taxes after VAT

        if (applyTaxes) {
            for (TaxData taxData : taxesAfterVat) {
                if (StockUtils.TaxAmountType.FIXED.getId() == taxData.getAmountType()) {
                    finalPrice += taxData.getAmount();
                } else {
                    finalPrice += taxData.getAmount() * sellPrice;
                }
            }
        }


        return finalPrice;

    }
}
