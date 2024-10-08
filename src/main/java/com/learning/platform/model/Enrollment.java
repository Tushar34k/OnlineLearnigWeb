package com.learning.platform.model;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Date date;
    private Double totalPrice;

    @OneToMany(mappedBy = "enrollment", fetch = FetchType.LAZY)
    private Set<Course> courses;  // Corrected to plural
    
     @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
