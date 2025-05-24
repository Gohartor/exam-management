package org.example.entity.person;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.entity.enumeration.PersonStatus;
import org.example.entity.base.BaseEntity;

@Setter
@Getter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Person extends BaseEntity {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Column(unique = true)
    @Size(min = 8, max = 50)
    private String userName;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private PersonStatus status;

}
