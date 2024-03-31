package com.in28minutes.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class CurrencyExchangeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	private Environment environment;
	
	private CurrencyExchangeRepository currencyExchangeRepository;

	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		LOGGER.info("[IN]CurrencyExchangeController - retrieveExchangeValue - from: {} - to: {}", from, to);
		String port = environment.getProperty("local.server.port");
		
//		Change Kubernetes
		String host = environment.getProperty("HOSTNAME");
		String version = "v12";
		
		CurrencyExchange currencyExchange = currencyExchangeRepository
				.findByFromAndTo(from, to).orElseThrow(() -> new RuntimeException("Unable to find data for " + from + " " + to));
		currencyExchange.setEnvirnoment(port + " " + version + " " + host);
		LOGGER.info("[OUT]CurrencyExchangeController - retrieveExchangeValue - currencyExchange: {}", currencyExchange);
		return currencyExchange;
	}
	

}
