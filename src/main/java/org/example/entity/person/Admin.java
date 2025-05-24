package org.example.entity.person;

import jakarta.persistence.Entity;
import lombok.*;
import org.example.entity.base.BaseEntity;


@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class Admin extends BaseEntity {
    
}
