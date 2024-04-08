package com.popcorn.college.repository;

import com.popcorn.college.entity.CollegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<CollegeEntity, Long> {

}
