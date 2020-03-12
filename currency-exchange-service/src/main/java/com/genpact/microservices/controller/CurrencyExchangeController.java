package com.genpact.microservices.controller;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.microservices.model.ExchangeValue;
import com.genpact.microservices.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {

	private static Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

	@Autowired
	private Environment env;

	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;

	/*
	 * @GetMapping("currency-exchange/from/{from}/to/{to}") public
	 * List<ExchangeValue> retriveExchangeValue(@PathVariable("from") String
	 * from, @PathVariable("to") String to) {
	 * 
	 * return new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65),
	 * Integer.parseInt(env.getProperty("local.server.port")));
	 * 
	 * return currencyExchangeRepository.findAllByFromAndTo(from, to); }
	 */

	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retriveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to) {
		ExchangeValue exchangeValue = currencyExchangeRepository.findByFromAndTo(from, to);
		exchangeValue.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		logger.info("{}", exchangeValue);
		return exchangeValue;
	}
}
