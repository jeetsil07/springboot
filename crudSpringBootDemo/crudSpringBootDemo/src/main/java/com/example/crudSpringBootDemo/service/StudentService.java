package com.example.crudSpringBootDemo.service;

import com.example.crudSpringBootDemo.dto.StudentRequestDto;
import com.example.crudSpringBootDemo.dto.StudentResponseDto;
import com.example.crudSpringBootDemo.entity.Student;
import com.example.crudSpringBootDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {
    StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public StudentResponseDto createStudent(StudentRequestDto studentReq) {
        Student student = mapToEntity(studentReq);
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        Student studentResp = studentRepository.save(student);
        return mapToDto(studentResp);
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
    private Student mapToEntity(StudentRequestDto studentReq) {
        Student student = new Student();
        student.setName(studentReq.getName());
        student.setAge(studentReq.getAge());
        student.setEmail(studentReq.getEmail());
        student.setRollNo(studentReq.getRollNo());
        student.setSubject(studentReq.getSubject());
        student.setDeleted(false);
        return student;
    }
    private StudentResponseDto mapToDto(Student student) {
        StudentResponseDto studentRespDto = new StudentResponseDto();
        studentRespDto.setId(student.getId());
        studentRespDto.setName(student.getName());
        studentRespDto.setAge(student.getAge());
        studentRespDto.setEmail(student.getEmail());
        studentRespDto.setRollNo(student.getRollNo());
        studentRespDto.setSubject(student.getSubject());
        studentRespDto.setCreatedAt(student.getCreatedAt());
        studentRespDto.setUpdatedAt(student.getUpdatedAt());
        studentRespDto.setMessage("Student created successfully");
        return studentRespDto;
    }
}
