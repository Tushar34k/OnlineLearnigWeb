package com.learning.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.platform.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
