package com.hzair.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hzair.demo.entity.Student;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    // define @PostConstruct to load the student data ... only once! // * Niiice
    @PostConstruct
    public void loadData() {
        theStudents = new ArrayList<>();

        theStudents.add(new Student("Peter", "Parker"));
        theStudents.add(new Student("Tony", "Stark"));
        theStudents.add(new Student("Bruce", "Banner"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() { // * No need to make a bind or something, Spring Boot will automatically use Jackson to convert the list of students to JSON format
        // * To be clear, Jackson will use setter and getter methods of the class (Student here) to make the binding to json or to POJO. So Getter and Setter are mandatory

        return theStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) { // * the param name should be the same as the path variable name.

        if (studentId >= theStudents.size() || studentId < 0) {
            // * Throw an exception if the studentId is invalid --> redirect to the exception handler method (handleException on our case)
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        return theStudents.get(studentId);
    }

    // * We move all these exception to the StudentRestExceptionHandler class to make the code cleaner and more readable and reusable. 

    // @ExceptionHandler //* StudentErrorResponse = Response type, StudentNotFoundException = Exception type
    // public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
    //     StudentErrorResponse error = new StudentErrorResponse();
    //     error.setStatus(HttpStatus.NOT_FOUND.value());
    //     error.setMessage(exc.getMessage());
    //     error.setTimestamp(System.currentTimeMillis());

    //     return  new ResponseEntity<>(error, HttpStatus.NOT_FOUND); // * ResponseEntity is a generic class that can be used to return any type of response. Here we are returning a StudentErrorResponse object with a 404 status code.
    // }

    // // * Add an exception handler to catch any exception (catch all except StudentNotFoundException) and return a generic error response
    // @ExceptionHandler
    // public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
    //             StudentErrorResponse error = new StudentErrorResponse();
    //     error.setStatus(HttpStatus.BAD_REQUEST.value());
    //     error.setMessage(exc.getMessage());
    //     error.setTimestamp(System.currentTimeMillis());

    //     return  new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); // * ResponseEntity is a generic class that can be used to return any type of response. Here we are returning a StudentErrorResponse object with a 404 status code.
    // }

}
