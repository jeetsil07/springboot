package org.example;

import org.example.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
//    @Autowired
    private PaymentService paymentService;

    @Autowired
    public OrderService(@Qualifier("cardPayment") PaymentService service) {
        this.paymentService = service;
    }

    public void placeOrder(){
        paymentService.pay();
        System.out.println("order placed");
    }
}
