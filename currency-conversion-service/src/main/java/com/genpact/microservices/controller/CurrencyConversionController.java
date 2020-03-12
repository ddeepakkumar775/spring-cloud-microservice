package com.genpact.microservices.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.genpact.microservices.feign.proxy.CurrencyExchangeServiceProxy;
import com.genpact.microservices.model.CurrencyConversionBean;

@RestController
public class CurrencyConversionController {

	@Autowired
	private Environment env;

	@Autowired
	private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

	@GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean retriveConversionValue(@PathVariable("from") String from,
			@PathVariable("to") String to, @PathVariable("quantity") BigDecimal quantity) {

		Map<String, String> map = new HashMap<>();
		map.put("from", from);
		map.put("to", to);

		RestTemplate responseEntity = new RestTemplate();
		CurrencyConversionBean forObject = responseEntity.getForObject(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, map);

		/*
		 * return new CurrencyConversionBean(1L, from, to, BigDecimal.valueOf(65),
		 * BigDecimal.valueOf(65), BigDecimal.valueOf(65),
		 * Integer.parseInt(env.getProperty("local.server.port")));
		 */

		return new CurrencyConversionBean(1L, from, to, forObject.getConversionMultiple(), forObject.getQuantity(),
				forObject.getConversionMultiple().multiply(quantity),
				Integer.parseInt(env.getProperty("local.server.port")));

	}

	@GetMapping("currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean retriveConversionValueFeign(@PathVariable("from") String from,
			@PathVariable("to") String to, @PathVariable("quantity") BigDecimal quantity) {

		Map<String, String> map = new HashMap<>();
		map.put("from", from);
		map.put("to", to);

		CurrencyConversionBean forObject = currencyExchangeServiceProxy.retriveExchagneValue(from, to);

		return new CurrencyConversionBean(1L, from, to, forObject.getConversionMultiple(), quantity,
				forObject.getConversionMultiple().multiply(quantity),
				Integer.parseInt(env.getProperty("local.server.port")));

	}
}
