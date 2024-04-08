package com.popcorn.college.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FullCollegeData {
    private Long collegeId;
    private String name;
    private String email;
    private List<StudentDto> students;
}
