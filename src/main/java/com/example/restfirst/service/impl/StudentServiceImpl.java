package com.example.restfirst.service.impl;

import com.example.restfirst.dto.ClientForUpdateDto;
import com.example.restfirst.model.Student;
import com.example.restfirst.repo.StudentRepo;
import com.example.restfirst.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public Optional<Student> getById(Long id) {
        return studentRepo.findById(id);
    }

    @Override
    public void saveStudent(Student student) {
        studentRepo.save(student);
    }

    @Override
    public void deleteStudent(Student student) {
        studentRepo.delete(student);
    }

    @Override
    public void updateStudent(Long id,ClientForUpdateDto clientForUpdateDto) {
        Student student = studentRepo.findById(id).orElseThrow();
        student.setName(clientForUpdateDto.getName());
        student.setSurname(clientForUpdateDto.getSurname());
        studentRepo.save(student);
    }
    @Override
    public List<Student> getAllStudents() {
       return studentRepo.findAll();
    }

    @Override
    public List<Student> getStudentsByNameContains(String key) {
        return studentRepo.findByNameContains(key);
    }
}
