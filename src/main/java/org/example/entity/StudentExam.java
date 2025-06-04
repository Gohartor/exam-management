package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.entity.base.BaseEntity;
import org.example.entity.person.Student;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student_exam")
public class StudentExam extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startedAt;


    private boolean finished;


    private Double score;
}