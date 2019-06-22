package dik.rentfare.controller;

import dik.rentfare.model.RentFare;
import dik.rentfare.service.RentFareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.MathContext;

@RestController
@RequestMapping(value = "/rent/{animal}/fare/{currency}")
public class RentFareController {

    @Autowired
    RentFareService rentFareService;

    @Value("${base.currency:RUB}")
    private String baseCurrency;

    @GetMapping
    public RentFare getAnimalFare(@PathVariable String animal, @PathVariable String currency) {
        RentFare fare = rentFareService.getRentFareByAnimal(animal);
        fare.setCurrency(currency);
        if (!baseCurrency.equals(currency)) {
            BigDecimal conversionRate = rentFareService.getConversion(currency);
            BigDecimal convertedFare = fare.getRentFare().multiply(conversionRate, MathContext.UNLIMITED);
            fare.setRentFare(convertedFare);
        }
        return fare;
    }
}
