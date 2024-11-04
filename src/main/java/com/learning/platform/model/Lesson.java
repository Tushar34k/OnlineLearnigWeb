package com.learning.platform.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Many lessons belong to one course
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;
      
    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    private List<Assignment> assignments;  // Corrected to plural
}
