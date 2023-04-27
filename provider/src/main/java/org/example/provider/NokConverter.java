package org.example.provider;

import org.example.service.CurrencyConverter;
import org.example.service.annotations.CurrencyString;

@CurrencyString("NOK")
public class NokConverter implements CurrencyConverter {
    @Override
    public double getConvertedCurrency(double value) {
        return value * 1.04;
    }
}
