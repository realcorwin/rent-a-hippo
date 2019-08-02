package dik.rentfare.service;

import dik.rentfare.model.CurrencyConversionVO;
import dik.rentfare.model.RentFare;
import dik.rentfare.repository.RentFareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@ConditionalOnProperty(name = "use", havingValue = "ribbon")
public class RentFareServiceWithRibbon implements RentFareService {

    @Autowired
    private RentFareRepository rentFareRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${base.currency:RUB}")
    private String baseCurrency;

    public RentFare getRentFareByAnimal(String animal) {
        return rentFareRepository.findFirstByAnimal(animal);
    }

    public BigDecimal getConversion(String toCurrency) {

        Map<String, String> urlPathVariables = new HashMap<>();
        urlPathVariables.put("from", baseCurrency);
        urlPathVariables.put("to", toCurrency);
        ResponseEntity<CurrencyConversionVO> responseEntity = restTemplate.getForEntity(
                "http://currency-conversion/currency/from/{from}/to/{to}", CurrencyConversionVO.class, urlPathVariables);
        CurrencyConversionVO converter = responseEntity.getBody();
        return converter.getConversionRate();

    }
}
