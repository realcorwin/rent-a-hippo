package dik.currencyconversion.controller;

import dik.currencyconversion.model.CurrencyConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/currency")
public class CurrencyConverterController {

    @GetMapping(value = "/from/{from}/to/{to}")
    public CurrencyConverter convertCurrency(@PathVariable String from, @PathVariable String to) {
        return new CurrencyConverter(1L, from, to, BigDecimal.valueOf(2L));
    }
}
