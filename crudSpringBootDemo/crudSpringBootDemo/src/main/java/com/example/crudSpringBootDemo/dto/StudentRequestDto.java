package com.example.crudSpringBootDemo.dto;

import jakarta.validation.constraints.*;

public class StudentRequestDto {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Min(value = 18, message = "Age must be a positive number")
    private int age;
    @Email(message = "Email should be valid")
    private String email;
    @NotNull(message = "Roll number is mandatory")
    private int rollNo;
    @NotBlank(message = "Subject is mandatory")
    private String subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
