package com.genpact.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableFeignClients("com.genpact.microservices.*")
@EnableDiscoveryClient
public class CurrencyConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	}
	
	@Bean
	public Sampler sampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}

//http://localhost:8765/currency-conversion-service/currency-conversion-feign/from/INR/to/EUR/quantity/120
//http://localhost:8100/currency-conversion-feign/from/INR/to/EUR/quantity/120
//http://localhost:8765/currency-exchange-service/currency-exchange/from/INR/to/EUR
//http://localhost:8001/currency-exchange/from/INR/to/EUR
//http://localhost:8888/limits-service/dev
//http://localhost:8080/limits
