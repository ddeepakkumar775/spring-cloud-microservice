package com.genpact.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.microservices.config.Configuration;
import com.genpact.microservices.model.LimitsConfiguration;

@RestController
public class LimitsConfigurationController {

	@Autowired
	private Configuration configuration;

	@GetMapping("limits")
	public LimitsConfiguration retriveLimitsFromConfiguration() {

		return new LimitsConfiguration(configuration.getMaximum(), configuration.getMinimum());
	}
}
