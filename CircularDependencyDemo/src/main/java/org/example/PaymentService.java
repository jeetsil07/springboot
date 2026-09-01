package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {
//    @Autowired
//    private OrderService orderService;
//    public PaymentService(OrderService orderService){
//        this.orderService = orderService;
//    }
    public void pay(){
        System.out.println("payment done");
//        not its responsibility
//        orderService.getOrderDetails();
    }
}
