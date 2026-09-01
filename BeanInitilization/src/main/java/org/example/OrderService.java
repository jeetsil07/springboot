package org.example;

import jdk.jfr.Label;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class OrderService {
    public OrderService() {
        System.out.println("Order Service Created");
    }
}
