package com.brandixi3.cidemo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brandixi3.cidemo.model.Item;
import com.brandixi3.cidemo.service.InventoryService;
import com.brandixi3.cidemo.service.PaymentService;
import com.brandixi3.cidemo.service.UserService;

@Controller
public class DemoController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value="/demo", method=RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute Item item, Model model) {
        model.addAttribute("item", item);
        return "result";
    }
    
}
