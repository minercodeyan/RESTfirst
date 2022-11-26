package com.example.restfirst.advice;


import com.example.restfirst.exceptions.NotFoundException;
import com.example.restfirst.exceptions.RegistrationException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<String> handleRegistrationException(RegistrationException e) {
        HttpHeaders h = new HttpHeaders();
        h.add("Content-type", "text/html;charset=UTF-8");

        return new ResponseEntity<>(e.getMessage(),h, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MailException.class)
    public ResponseEntity<String> handleEmailException(MailException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(
                (error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(fieldName, message);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }



}
