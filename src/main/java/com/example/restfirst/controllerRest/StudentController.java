package com.example.restfirst.controllerRest;

import com.example.restfirst.dto.ClientForUpdateDto;
import com.example.restfirst.dto.StudentDto;
import com.example.restfirst.exceptions.NotFoundException;
import com.example.restfirst.model.JSONViews.Views;
import com.example.restfirst.model.Student;
import com.example.restfirst.service.StudentService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudent(@PathVariable("id") Long studentId) {
        return new ResponseEntity<>(studentService.getById(studentId), HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveClient(@RequestBody @Valid Student student) {
        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStudent(@PathVariable("id") Long id, @RequestBody ClientForUpdateDto clientForUpdateDto, UriComponentsBuilder uriComponentsBuilder) {
        studentService.updateStudent(id,clientForUpdateDto);
        return new ResponseEntity<>("updated", HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") Long id) {
        Student student = studentService.getById(id);
        studentService.deleteStudent(student);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({Views.IdName.class})
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @PostMapping(value = "group",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> saveStudentsGroup(@RequestBody List<StudentDto> studentsForUpdate){
        studentService.saveStudentsGroup(studentsForUpdate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

