package com.example.demoAop.service;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
    public String createStudent() {
        System.out.println("student saved");
        try{
            throw new RuntimeException("Exception occurred while creating student");
        }catch (Exception e){

        }

         return "Student created successfully";
    }
}
