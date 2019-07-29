package dik.rentfare.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
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
@ConditionalOnProperty(name="use", havingValue="eureka")
public class RentFareServiceWithEureka implements RentFareService {

    @Autowired
    private RentFareRepository rentFareRepository;

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    @Value("${base.currency:RUB}")
    private String baseCurrency;

    public RentFare getRentFareByAnimal(String animal) {
        return rentFareRepository.findFirstByAnimal(animal);
    }

    public BigDecimal getConversion(String toCurrency) {

        Application app = eurekaClient.getApplication("currency-conversion");
        List<InstanceInfo> instances = app.getInstances();

        String serviceUri = String.format("%scurrency/from/{from}/to/{to}", instances.get(0).getHomePageUrl()); // http://localhost:7101/

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> urlPathVariables = new HashMap<>();
        urlPathVariables.put("from", baseCurrency);
        urlPathVariables.put("to", toCurrency);
        ResponseEntity<CurrencyConversionVO> responseEntity = restTemplate.getForEntity(
                serviceUri, CurrencyConversionVO.class, urlPathVariables);
        CurrencyConversionVO converter = responseEntity.getBody();
        return converter.getConversionRate();

    }
}
