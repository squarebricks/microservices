package com.popcorn.student.repository;

import com.popcorn.student.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    List<StudentEntity> findAllByCollegeId(Long collegeId);
}
