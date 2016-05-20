package com.brandixi3.cidemo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brandixi3.cidemo.model.Item;
import com.brandixi3.cidemo.service.InventoryService;

@Controller
public class DemoController {

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping(value="/demo", method=RequestMethod.GET)
    public String itemView( Model model) {
    	model.addAttribute("item", new Item());
    	return "demo";
    }
    
    @RequestMapping(value="/demo", method=RequestMethod.POST)
    public String itemSubmit(@ModelAttribute("item") Item item, Model model) {
    	System.out.println("=============");
    	System.out.println(item.getContent());
    	System.out.println(item.getId());
    	
    	inventoryService.addItem(item);
        model.addAttribute("item", item);
        return "result";
    }
    
}
