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
        studentReq.setDeleted(false);
        return studentRepository.save(studentReq);
    }

    public Student getStudent(Long id) {

        return studentRepository.findByIdAndIsDeletedFalse(id).orElse(null);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAllByIsDeletedFalse();
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student existingStudent = studentRepository.findByIdAndIsDeletedFalse(id).orElse(null);
        if (existingStudent != null) {
            existingStudent.setName(studentDetails.getName());
            existingStudent.setEmail(studentDetails.getEmail());
            existingStudent.setAge(studentDetails.getAge());
            existingStudent.setRollNo(studentDetails.getRollNo());
            existingStudent.setSubject(studentDetails.getSubject());
            existingStudent.setDeleted(false);
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

    public Boolean deleteStudentSoft(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setDeleted(true);
            studentRepository.save(student);
            return true;
        }
        return false;
    }
}
