package com.popcorn.student.controller;

import com.popcorn.student.entity.StudentEntity;
import com.popcorn.student.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@Slf4j
public class StudentController {
    private final StudentRepository studentRepository;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseStatus(HttpStatus.CREATED)
    public StudentEntity create(
            @RequestBody StudentEntity student,
            HttpServletRequest request
    ) {
        log.info("StudentController::create");
        return studentRepository.save(student);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<StudentEntity>> getAll(HttpServletRequest request) {
        log.info("StudentController::getAll");
        return ResponseEntity.status(HttpStatus.OK)
                .body(studentRepository.findAll());
    }

    @GetMapping(
            path = "/{studentId}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<StudentEntity> getById(
            @PathVariable(name = "studentId") final Long studentId,
            HttpServletRequest request
    ) {
        log.info("StudentController::getById");
        return ResponseEntity.status(HttpStatus.OK)
                .body(studentRepository.findById(studentId).orElse(null));
    }

    @GetMapping(
            path = "/college/{collegeId}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<StudentEntity>> getAllStudentsInACollege(
            @PathVariable(name = "collegeId") final Long collegeId,
            HttpServletRequest request
    ) {
        log.info("StudentController::getAllStudentsInACollege");
        return ResponseEntity.status(HttpStatus.OK)
                .body(studentRepository.findAllByCollegeId(collegeId));
    }
}
