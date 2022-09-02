package com.example.restfirst.repo;

import com.example.restfirst.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student,Long> {

    List<Student> findByNameContains(String name);

    void deleteById(Long id);
}
