package ru.fixies.models;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categories")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @Length(max = 255, message = "The length of the category name cannot exceed 255 characters!")
    private String name;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private List<Model> models;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return id != null && Objects.equals(id, category.id) && Objects.equals(name, category.name) && Objects.equals(models, category.models);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, models);
    }
}
