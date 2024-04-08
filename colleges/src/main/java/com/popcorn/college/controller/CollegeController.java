package com.popcorn.college.controller;

import com.popcorn.college.client.StudentClient;
import com.popcorn.college.dto.FullCollegeData;
import com.popcorn.college.entity.CollegeEntity;
import com.popcorn.college.repository.CollegeRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/colleges")
@RequiredArgsConstructor
@Slf4j
public class CollegeController {
    private final CollegeRepository collegeRepository;
    private final StudentClient studentClient;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseStatus(HttpStatus.CREATED)
    public CollegeEntity create(
            @RequestBody CollegeEntity college,
            HttpServletRequest request
    ) {
        log.info("CollegeController::create");
        return collegeRepository.save(college);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<CollegeEntity>> getAll(HttpServletRequest request) {
        log.info("CollegeController::getAll");
        return ResponseEntity.status(HttpStatus.OK)
                .body(collegeRepository.findAll());
    }
    @GetMapping(
            path = "/{collegeId}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<CollegeEntity> getById(
            @PathVariable(name = "collegeId") final Long collegeId,
            HttpServletRequest request
    ) {
        log.info("CollegeController::getById");
        return ResponseEntity.status(HttpStatus.OK)
                .body(collegeRepository.findById(collegeId).orElse(null));
    }
    @GetMapping(
            path = "/{collegeId}/students",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<FullCollegeData> getAllStudentsInCollege(
            @PathVariable(name = "collegeId") final Long collegeId,
            HttpServletRequest request
    ) {
        log.info("CollegeController::getAllStudentsInCollege");
        var college = collegeRepository.findById(collegeId).orElse(
                CollegeEntity.builder()
                        .collegeId(collegeId)
                        .name("NOT_FOUND")
                        .email("NOT_FOUND")
                        .build()
        );
        var students = studentClient.findAllStudentsByCollege(collegeId); // need to fetch from students microservice
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        FullCollegeData.builder()
                                .collegeId(collegeId)
                                .name(college.getName())
                                .email(college.getEmail())
                                .students(students)
                                .build()
                );
    }
}
