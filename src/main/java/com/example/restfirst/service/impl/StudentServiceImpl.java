package com.example.restfirst.service.impl;

import com.example.restfirst.dto.ClientForUpdateDto;
import com.example.restfirst.dto.StudentDto;
import com.example.restfirst.exceptions.NotFoundException;
import com.example.restfirst.model.GroupUni;
import com.example.restfirst.model.Student;
import com.example.restfirst.repo.StudentRepo;
import com.example.restfirst.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Student getById(Long id) {
        return studentRepo.findById(id).orElseThrow(() -> new NotFoundException("student"));
    }

    @Override
    public Student saveStudent(Student student) {
       return studentRepo.save(student);
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
    public void saveStudentsGroup(List<StudentDto> studentsForUpdate) {
        List<Student> students = new ArrayList<>();
        for(StudentDto st : studentsForUpdate){
            Student student = new Student();
            student.setId(st.getId());
            student.setGroupUni(new GroupUni(st.getGroup_id()));
            student.setSex(st.getSex());
            student.setName(st.getName());
            student.setSurname(st.getSurname());
            student.setPatronymic(st.getPatronymic());
            students.add(student);
        }
        studentRepo.saveAll(students);
    }

}
