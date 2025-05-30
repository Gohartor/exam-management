package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class Exam extends BaseEntity {


    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<Question> questions;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private int duration ;

    @ManyToOne
    private Teacher teacher;

}
