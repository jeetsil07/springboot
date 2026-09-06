package com.example.crudSpringBootDemo.service;

import com.example.crudSpringBootDemo.entity.Student;
import com.example.crudSpringBootDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student createStudent(Student studentReq) {
        return studentRepository.save(studentReq);
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent != null) {
            existingStudent.setName(studentDetails.getName());
            existingStudent.setEmail(studentDetails.getEmail());
            existingStudent.setAge(studentDetails.getAge());
            existingStudent.setRollNo(studentDetails.getRollNo());
            existingStudent.setSubject(studentDetails.getSubject());
            return studentRepository.save(existingStudent);
        }
        return null;
    }

    public Boolean deleteStudent(Long id) {
        Boolean isStudentExist = studentRepository.existsById(id);
        if (!isStudentExist) {
            return false;
        }
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            studentRepository.delete(student);
            return true;
        }
        return false;
    }
}
