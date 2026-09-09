package com.example.crudSpringBootDemo.service;

import com.example.crudSpringBootDemo.dto.StudentRequestDto;
import com.example.crudSpringBootDemo.dto.StudentResponseDto;
import com.example.crudSpringBootDemo.dto.UpdateStudentRequestDto;
import com.example.crudSpringBootDemo.entity.Student;
import com.example.crudSpringBootDemo.exception.DuplicateResourceException;
import com.example.crudSpringBootDemo.exception.ResourceNotFoundException;
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
        if(emailExists(student)) {
            throw new DuplicateResourceException("Email already exists"+ student.getEmail());
        }
        Student studentResp = studentRepository.save(student);
        return mapToDto(studentResp);
    }

    public StudentResponseDto getStudent(Long id) {

        Student studentResp = studentRepository.findByIdAndIsDeletedFalse(id).orElseThrow(()-> new ResourceNotFoundException("Student not found with id: " + id));
        return mapToDto(studentResp);
    }

    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAllByIsDeletedFalse().stream().map(this::mapToDto).collect(java.util.stream.Collectors.toList());
    }

    public StudentResponseDto updateStudent(Long id, UpdateStudentRequestDto studentDetails) {
        Student existingStudent = studentRepository.findByIdAndIsDeletedFalse(id).orElseThrow(()-> new ResourceNotFoundException("Student not found with id: " + id));

        existingStudent.setName(studentDetails.getName());
        existingStudent.setAge(studentDetails.getAge());
        existingStudent.setRollNo(studentDetails.getRollNo());
        existingStudent.setSubject(studentDetails.getSubject());
        existingStudent.setDeleted(false);
        existingStudent.setUpdatedAt(LocalDateTime.now());
        Student updatedStudent = studentRepository.save(existingStudent);
        return mapToDto(updatedStudent);

    }

    public void deleteStudent(Long id) {
        Student isStudentExist = studentRepository.
                findById(id).orElseThrow(()-> new ResourceNotFoundException("Student not found with id: " + id));

       studentRepository.delete(isStudentExist);
    }

    public void deleteStudentSoft(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student not found with id: " + id));
        student.setDeleted(true);
        studentRepository.save(student);
    }
    private Student mapToEntity(StudentRequestDto studentReq) {
        Student student = new Student();
        student.setName(studentReq.getName());
        student.setAge(studentReq.getAge());
        student.setEmail(studentReq.getEmail());
        student.setRollNo(studentReq.getRollNo());
        student.setSubject(studentReq.getSubject());
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
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

    private boolean emailExists(Student student) {
        return studentRepository.existsByEmail(student.getEmail());
    }
}
