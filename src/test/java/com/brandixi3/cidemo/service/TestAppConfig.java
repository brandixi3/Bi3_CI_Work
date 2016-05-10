package com.brandixi3.cidemo.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.brandixi3.cidemo.service.impl.InventoryServiceImpl;

@Configuration
public class TestAppConfig {
	@Bean
	public InventoryService getSampleService() {
		return new InventoryServiceImpl();
	}
}