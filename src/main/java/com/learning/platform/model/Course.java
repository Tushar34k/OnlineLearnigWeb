package com.learning.platform.model;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Date startDate;
    private Date endDate;
    private int duration;
    private Double price;
    private Double installment;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY) // Avoid CascadeType.ALL in ManyToMany
    private Set<User> users;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Lesson> lessons;  // Corrected to plural

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private Set<Quize> quizzes;  // Corrected class and field name
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Enrollment enrollment;
}
