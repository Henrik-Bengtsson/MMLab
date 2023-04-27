package org.example.provider;

import org.example.service.CurrencyConverter;
import org.example.service.annotations.CurrencyString;

@CurrencyString("YEN")
public class YenConverter implements CurrencyConverter {
    @Override
    public double getConvertedCurrency(double value) {
        return value * 12.99;
    }
}
