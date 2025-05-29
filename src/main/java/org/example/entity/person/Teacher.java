package org.example.entity.person;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.example.entity.Course;

import java.util.List;

@Entity
@Setter
@Getter
@ToString

public class Teacher extends Person {

    @NotBlank
    @Positive
    private double salary;

    @Email
    private String mail;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Course> courses;
}
