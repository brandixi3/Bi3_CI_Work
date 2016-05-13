package com.brandixi3.cidemo.service.impl;

import org.springframework.stereotype.Service;

import com.brandixi3.cidemo.dao.InventryDao;
import com.brandixi3.cidemo.model.Item;
import com.brandixi3.cidemo.service.InventoryService;

@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {

	private InventryDao inventryDao;
	
    @Override
    public void addItem(Item item) {        
        inventryDao.createItem(item);
    }
}
