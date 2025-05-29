package org.example.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.example.entity.base.BaseEntity;

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
}
