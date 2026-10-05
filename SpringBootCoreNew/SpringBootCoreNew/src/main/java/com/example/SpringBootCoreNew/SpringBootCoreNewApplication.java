package com.example.SpringBootCoreNew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootCoreNewApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootCoreNewApplication.class, args);
		OrderService orderService = context.getBean(OrderService.class);
		orderService.placOrder();
	}

}
