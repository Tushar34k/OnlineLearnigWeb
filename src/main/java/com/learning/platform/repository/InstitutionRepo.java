package com.learning.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.platform.model.Institution;

public interface InstitutionRepo extends JpaRepository<Institution, Long> {

}
