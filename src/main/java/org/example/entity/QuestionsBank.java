package org.example.entity;


import jakarta.persistence.*;
import lombok.*;
import org.example.entity.base.BaseEntity;
import org.example.entity.person.Teacher;

import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuestionsBank extends BaseEntity {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> questions;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToOne(mappedBy = "questionsBank")
    private Course course;
}
