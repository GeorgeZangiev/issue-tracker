package com.elmojke.issuetracker.developer;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GenerationType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Developer {

    @Id
    @SequenceGenerator(
            name = "developer_id_sequence",
            sequenceName = "developer_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "developer_id_sequence"
    )
    private Integer id;
    @NonNull
    private String name;
}

