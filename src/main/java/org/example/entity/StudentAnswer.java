package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.entity.base.BaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student_answer")
public class StudentAnswer extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "student_exam_id")
    private StudentExam studentExam;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;


    private String answerText;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private Option selectedOption;

    private Double score;
}