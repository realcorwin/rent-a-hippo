package dik.rentfare.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import dik.rentfare.feign.CurrencyConversionServiceProxy;
import dik.rentfare.model.CurrencyConversionVO;
import dik.rentfare.model.RentFare;
import dik.rentfare.repository.RentFareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@ConditionalOnProperty(name="use", havingValue="feign")
public class RentFareServiceWithFeign implements RentFareService {

    @Autowired
    private RentFareRepository rentFareRepository;

    @Autowired
    private CurrencyConversionServiceProxy feignProxy;

    @Value("${base.currency:RUB}")
    private String baseCurrency;

    public RentFare getRentFareByAnimal(String animal) {
        return rentFareRepository.findFirstByAnimal(animal);
    }

    public BigDecimal getConversion(String toCurrency) {

            CurrencyConversionVO converter = feignProxy.convertCurrency(baseCurrency, toCurrency);
            return converter.getConversionRate();

    }
}
