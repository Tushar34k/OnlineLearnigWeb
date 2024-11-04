package com.learning.platform.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.platform.model.Course;
import com.learning.platform.model.Enrollment;
import com.learning.platform.model.User;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

	Optional<Enrollment> findByName(String name);
	
	 boolean existsByUserAndCourses(User user, Set<Course> course);

}
