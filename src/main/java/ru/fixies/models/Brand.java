package ru.fixies.models;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "brands")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @Length(min = 1, max = 255, message = "The length of the brands name cannot exceed 255 characters!")
    private String name;

    @OneToMany(mappedBy = "brand")
    @ToString.Exclude
    private List<Model> models;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brand brand)) return false;
        return id != null && Objects.equals(id, brand.id) && Objects.equals(name, brand.name) && Objects.equals(models, brand.models);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, models);
    }
}
