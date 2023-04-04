package com.elmojke.issuetracker.developer;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Developer developer = (Developer) o;
        return getId() != null && Objects.equals(getId(), developer.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

