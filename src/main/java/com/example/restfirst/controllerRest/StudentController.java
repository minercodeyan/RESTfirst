package com.example.restfirst.controllerRest;

import com.example.restfirst.dto.ClientForUpdateDto;
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
        if (studentId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Student student = studentService.getById(studentId).orElse(null);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveClient(@RequestBody @Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors =bindingResult.getFieldErrors();
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
        if (student == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        studentService.saveStudent(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStudent(@PathVariable("id") Long id, @RequestBody ClientForUpdateDto clientForUpdateDto, UriComponentsBuilder uriComponentsBuilder) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        studentService.updateStudent(id,clientForUpdateDto);
        return new ResponseEntity<>("updated", HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") Long id) {
        Student student = studentService.getById(id).orElse(null);
        if (student==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        studentService.deleteStudent(student);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({Views.IdName.class})
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> studentList = studentService.getAllStudents();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }



}

