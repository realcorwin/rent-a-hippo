package dik.rentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.h2.tools.Console;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.sql.SQLException;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class RentServiceApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(RentServiceApplication.class, args);
	}

}
