package com.brandixi3.cidemo.service;

import java.util.Date;
import java.util.List;

import com.brandixi3.cidemo.model.Payment;

public interface PaymentService {

    void checkout(Payment payment);

    void savePayment(String userId, List<Item> items);

    void saveCreditCard(String validName, Date expiry, String cardNumber);

}
