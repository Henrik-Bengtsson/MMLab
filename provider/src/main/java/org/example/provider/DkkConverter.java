package org.example.provider;

import org.example.service.CurrencyConverter;
import org.example.service.annotations.CurrencyString;

@CurrencyString("DKK")
public class DkkConverter implements CurrencyConverter {
    @Override
    public double getConvertedCurrency(double value) {
        return value * 0.66;
    }
}
