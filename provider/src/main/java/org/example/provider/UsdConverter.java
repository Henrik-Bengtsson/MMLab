package org.example.provider;

import org.example.service.CurrencyConverter;
import org.example.service.annotations.CurrencyString;

@CurrencyString("USD")
public class UsdConverter implements CurrencyConverter {
    @Override
    public double getConvertedCurrency(double value) {
        return value * 0.097;
    }
}
