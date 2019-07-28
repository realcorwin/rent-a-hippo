package dik.currencyconversion;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.sql.SQLException;

@SpringBootApplication
@EnableEurekaClient
public class CurrencyConversionApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(CurrencyConversionApplication.class, args);

		//Console.main(args);
	}

}
