package com.in28minutes.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@SpringBootApplication
public class CurrencyExchangeServiceApplication implements CommandLineRunner {

	private CurrencyExchangeRepository currencyExchangeRepository;

	private static int idCount = 0;

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		currencyExchangeRepository.save(new CurrencyExchange((long) ++idCount, "USD", "INR", new BigDecimal("65"), ""));
		currencyExchangeRepository.save(new CurrencyExchange((long) ++idCount, "EUR", "INR", new BigDecimal("75"), ""));
		currencyExchangeRepository.save(new CurrencyExchange((long) ++idCount, "AUD", "INR", new BigDecimal("25"), ""));
	}

}
