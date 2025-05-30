package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.example.entity.base.BaseEntity;
import org.example.entity.enumeration.QuestionType;
import org.example.entity.person.Teacher;

import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Question extends BaseEntity {

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    @Positive
    @NotBlank
    private int score;


    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Option> options;


    @Enumerated(EnumType.STRING)
    @NotNull
    private QuestionType questionType;


    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;


    @ManyToOne
    @JoinColumn(name = "questions_bank_id")
    private QuestionsBank questionsBank;


    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<ExamQuestion> examQuestions;

}
