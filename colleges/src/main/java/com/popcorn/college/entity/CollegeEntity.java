package com.popcorn.college.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "colleges"
)
public class CollegeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long collegeId;
    private String name;
    private String email;
}
