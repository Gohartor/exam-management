package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.example.entity.base.BaseEntity;
import org.example.entity.person.Student;
import org.example.entity.person.Teacher;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseEntity {

    @NotBlank
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startTime;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Exam> exam;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "questions_bank_id")
    private QuestionsBank questionsBank;

}
