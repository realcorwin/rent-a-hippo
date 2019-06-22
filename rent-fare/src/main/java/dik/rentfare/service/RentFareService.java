package dik.rentfare.service;

import dik.rentfare.model.CurrencyConversionVO;
import dik.rentfare.model.RentFare;
import dik.rentfare.repository.RentFareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class RentFareService {

    @Autowired
    private RentFareRepository rentFareRepository;

    @Autowired
    RestTemplate restTemplate;

    //@Autowired
    //private EurekaClient eurekaClient;

    //@Autowired
    //private CurrencyConversionServiceProxy feignProxy;

    @Value("${use.eureka.client:false}")
    private boolean useEurekaClient;

    @Value("${use.ribbon.backed.rest.template:false}")
    private boolean useRibbonBackedRestTemplate;

    @Value("${use.feign.client:false}")
    private boolean useFeignClient;

    @Value("${base.currency:RUB}")
    private String baseCurrency;

    public RentFare getRentFareByAnimal(String animal) {
        return rentFareRepository.findFirstByAnimal(animal);
    }

    public BigDecimal getConversion(String toCurrency) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> urlPathVariables = new HashMap<>();
        urlPathVariables.put("from", baseCurrency);
        urlPathVariables.put("to", toCurrency);
        ResponseEntity<CurrencyConversionVO> responseEntity = restTemplate.getForEntity(
                "http://localhost:7101/currency/from/{from}/to/{to}", CurrencyConversionVO.class, urlPathVariables);
        CurrencyConversionVO converter = responseEntity.getBody();
        return converter.getConversionRate();
    }
}
