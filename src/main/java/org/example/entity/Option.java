package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.entity.base.BaseEntity;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Option extends BaseEntity {

    @NotBlank
    private String text;

    @NotNull
    private Boolean correct;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}