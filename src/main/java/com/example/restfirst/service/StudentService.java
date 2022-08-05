package com.example.restfirst.service;

import com.example.restfirst.dto.ClientForUpdateDto;
import com.example.restfirst.dto.StudentDto;
import com.example.restfirst.model.Student;

import java.util.List;
import java.util.Optional;


public interface StudentService {

    Optional<Student> getById(Long id);

    void saveStudent(Student student);

    void deleteStudent(Student student);

    void updateStudent(Long id,ClientForUpdateDto clientForUpdateDto);

    List<Student> getAllStudents();

    void saveStudentsGroup(List<StudentDto> studentsForUpdate);


}
