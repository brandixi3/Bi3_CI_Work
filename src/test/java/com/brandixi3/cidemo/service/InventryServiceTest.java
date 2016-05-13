package com.brandixi3.cidemo.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfig.class, loader = AnnotationConfigContextLoader.class)
public class InventryServiceTest {

	@Autowired
	private InventoryService inventoryService;

	@Test
	public void testInventryService() {
		assertEquals("class com.brandixi3.cidemo.service.impl.InventoryServiceImpl",
				this.inventoryService.getClass().toString());
	}
	
	@Test
	public void testAddItem(){
		//this.inventoryService.addItem(new Item());
	}
}
