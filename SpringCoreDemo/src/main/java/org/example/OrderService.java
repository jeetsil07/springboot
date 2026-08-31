package org.example;

import org.springframework.stereotype.Component;

@Component
public class OrderService {
    private PaymentService paymentService;

    public OrderService(PaymentService service) {
        this.paymentService = service;
    }

    public void placeOrder(){
        paymentService.pay();
        System.out.println("order placed");
    }
}
