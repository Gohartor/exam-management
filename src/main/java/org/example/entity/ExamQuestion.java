package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.entity.base.BaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exam_question")
public class ExamQuestion extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private double score;
}