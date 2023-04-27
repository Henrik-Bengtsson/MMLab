import org.example.provider.*;
import org.example.service.CurrencyConverter;

module org.example.provider {
    requires org.example.service;
    provides CurrencyConverter with DkkConverter, EuroConverter, NokConverter, UsdConverter, YenConverter;
}