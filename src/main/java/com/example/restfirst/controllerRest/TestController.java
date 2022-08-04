package com.example.restfirst.controllerRest;

import com.example.restfirst.model.GroupUni;
import com.example.restfirst.model.Student;
import com.example.restfirst.repo.GroupRepo;
import com.example.restfirst.repo.StudentRepo;
import com.example.restfirst.utils.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/test")
public class TestController {

    @Autowired
    private StudentRepo studentRepo;



    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getServerTime() {
        List<Student> students = DataGenerator.generateRandomStudents(12);
      // studentRepo.saveAll(students);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
