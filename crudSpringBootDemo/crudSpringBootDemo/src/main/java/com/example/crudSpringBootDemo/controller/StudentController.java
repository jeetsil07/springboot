package com.example.crudSpringBootDemo.controller;

import com.example.crudSpringBootDemo.dto.StudentRequestDto;
import com.example.crudSpringBootDemo.dto.StudentResponseDto;
import com.example.crudSpringBootDemo.dto.UpdateStudentRequestDto;
import com.example.crudSpringBootDemo.entity.Student;
import com.example.crudSpringBootDemo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //create
    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@Valid @RequestBody StudentRequestDto student) {
        StudentResponseDto createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }
    //read
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudent(@PathVariable Long id) {
        StudentResponseDto student = studentService.getStudent(id);
        return ResponseEntity.ok(student);

    }
    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    //update
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable Long id, @RequestBody UpdateStudentRequestDto student) {
        StudentResponseDto studentResp = studentService.updateStudent(id, student);
        return ResponseEntity.ok(studentResp);
    }
    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/soft-delete/{id}")
    public ResponseEntity<String> deleteStudentSoft(@PathVariable Long id) {
       studentService.deleteStudentSoft(id);
       return ResponseEntity.noContent().build();
    }
}
