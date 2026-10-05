package com.example.demoAop.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    @Before("execution(* com.example.demoAop.service.StudentService.createStudent())")
    public void logBeforeMethodExecution() {
        System.out.println("Student is going to be created");
    }

    @After("execution(* com.example.demoAop.service.StudentService.createStudent())")
    public void logAfterMethodExecution() {
        System.out.println("Student has been created");
    }

    @AfterThrowing(pointcut = "execution(* com.example.demoAop.service.StudentService.createStudent())", throwing = "ex")
    public void logAfterReturningMethodExecutionWithException(RuntimeException ex) {
        System.out.println("Exception occurred while creating student: " + ex.getMessage());
    }
}
