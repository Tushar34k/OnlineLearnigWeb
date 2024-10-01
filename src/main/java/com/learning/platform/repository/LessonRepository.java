package com.learning.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.platform.model.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

}
