package com.brandixi3.cidemo.service;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.brandixi3.cidemo.dao.InventryDao;
import com.brandixi3.cidemo.service.impl.InventoryServiceImpl;

/**
 * The Class TestAppConfig.
 */
@Configuration
public class TestAppConfig {
	
	/**
	 * Gets the sample service.
	 *
	 * @return the sample service
	 */
	@Bean
	public InventoryService getSampleService() {
		return new InventoryServiceImpl();
	}

	/**
	 * My mock.
	 *
	 * @return the inventry dao
	 */
	@Bean
	public InventryDao myMock() {
		return Mockito.mock(InventryDao.class);
	}
}