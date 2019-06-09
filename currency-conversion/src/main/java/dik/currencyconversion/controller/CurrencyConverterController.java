package dik.currencyconversion.controller;

import dik.currencyconversion.model.CurrencyConverter;
import dik.currencyconversion.repository.CurrencyConverterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/currency")
public class CurrencyConverterController {

    private final CurrencyConverterRepository currencyConverterRepository;

    public CurrencyConverterController(CurrencyConverterRepository currencyConverterRepository) {
        this.currencyConverterRepository = currencyConverterRepository;
    }

    @GetMapping(value = "/from/{from}/to/{to}")
    public CurrencyConverter convertCurrency(@PathVariable String from, @PathVariable String to) {
        return currencyConverterRepository.findFirstByCurrencyFromAndCurrencyTo(from, to);
    }
}
