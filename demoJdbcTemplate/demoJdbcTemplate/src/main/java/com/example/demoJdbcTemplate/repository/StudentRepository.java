package com.example.demoJdbcTemplate.repository;

import com.example.demoJdbcTemplate.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class StudentRepository {
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Student> studentRowMapper = new StudentRowMapper();

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createStudent(Student student){
        String sql  = """
            INSERT INTO student (name, email, age) VALUES (?,?,?)
            """;
        int affectedRows = jdbcTemplate.update(sql, student.getName(), student.getEmail(), student.getAge());
        if (affectedRows == 1){
            System.out.println("Student created successfully");
        }else{
            System.out.println("Failed to create student");
        }
    }

    public List<Student> getStudent() {
        String sql = "SELECT * FROM student";
        List<Student> allStudents = jdbcTemplate.query(sql, studentRowMapper);
        return allStudents;
    }

    public Student getStudentById(Long id) {
        String sql = "SELECT * FROM student WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, studentRowMapper, id);
    }

    public void updateStudent(Student student, Long id) {
        String sql = "UPDATE student SET name = ?, email = ?, age = ? WHERE id = ?";
        int affectedRows = jdbcTemplate.update(sql, student.getName(), student.getEmail(), student.getAge(), id);
        if (affectedRows == 1){
            System.out.println("Student updated successfully");
        }else{
            System.out.println("Failed to update student");
        }
    }

    public void deleteStudent(Long id) {
        String sql = "DELETE FROM student WHERE id = ?";
        int affectedRows = jdbcTemplate.update(sql, id);
        if (affectedRows == 1){
            System.out.println("Student deleted successfully");
        }else{
            System.out.println("Failed to delete student");
        }
    }
}
