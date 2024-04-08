package com.popcorn.college.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {
    private Long studentId;
    private String name;
    private String email;
}
