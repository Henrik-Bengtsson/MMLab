package org.example.provider;

import org.example.service.CurrencyConverter;
import org.example.service.annotations.CurrencyString;

@CurrencyString("EURO")
public class EuroConverter implements CurrencyConverter {

    @Override
    public double getConvertedCurrency(double value) {
        return value * 0.088;
    }
}
