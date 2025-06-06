package org.example.entity.person;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.example.entity.Course;

import java.util.List;

@Entity
@Setter
@Getter
@ToString

public class Student extends Person {

    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;

    @Positive
    private double average;

}
