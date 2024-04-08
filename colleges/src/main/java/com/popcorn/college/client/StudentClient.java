package com.popcorn.college.client;

import com.popcorn.college.dto.StudentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "student-service", url = "${application.config.students-url}")
public interface StudentClient {

    @GetMapping("/college/{collegeId}")
    List<StudentDto> findAllStudentsByCollege(@PathVariable(name = "collegeId") Long collegeId);
}
