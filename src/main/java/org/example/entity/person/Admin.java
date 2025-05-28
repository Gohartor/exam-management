package org.example.entity.person;

import jakarta.persistence.Entity;
import lombok.*;
import org.example.entity.base.BaseEntity;
import org.example.entity.enumeration.PersonStatus;

import java.time.LocalDateTime;


@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends Person {



}
