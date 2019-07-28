package dik.currencyconversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.sql.SQLException;

@SpringBootApplication
@EnableEurekaClient
public class CurrencyConversion2Application {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversion2Application.class, args);
	}

}
