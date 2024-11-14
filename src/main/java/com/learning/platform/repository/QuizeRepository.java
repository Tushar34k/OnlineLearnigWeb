package com.learning.platform.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.platform.model.Quiz;



public interface QuizeRepository extends JpaRepository<Quiz, Long> {

	Optional<Quiz> findByName(String name);

}
