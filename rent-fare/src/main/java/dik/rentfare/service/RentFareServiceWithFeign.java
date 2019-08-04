package dik.rentfare.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
import java.util.Random;

@Service
@ConditionalOnProperty(name="use", havingValue="feign")
public class RentFareServiceWithFeign implements RentFareService {

    @Autowired
    private RentFareRepository rentFareRepository;

    @Autowired
    private CurrencyConversionServiceProxy feignProxy;

    @Value("${base.currency:RUB}")
    private String baseCurrency;

    @HystrixCommand(commandProperties= {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="7000")
    })
    public RentFare getRentFareByAnimal(String animal) {
        //sleepRandomly();
        return rentFareRepository.findFirstByAnimal(animal);
    }

    private void sleepRandomly() {
        Random rand = new Random();
        int randomNum = rand.nextInt(3) + 1;
        if(randomNum == 3) {
            System.out.println("It is a chance for demonstrating Hystrix action");
            try {
                System.out.println("Start sleeping...." + System.currentTimeMillis());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Hystrix thread interupted...." + System.currentTimeMillis());
                e.printStackTrace();
            }
        }
    }

    public BigDecimal getConversion(String toCurrency) {

            CurrencyConversionVO converter = feignProxy.convertCurrency(baseCurrency, toCurrency);
            return converter.getConversionRate();

    }
}
