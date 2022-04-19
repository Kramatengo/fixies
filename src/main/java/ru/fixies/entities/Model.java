package ru.fixies.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "models")
@Getter
@Setter
@NoArgsConstructor
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Range(max = 255, message = "The length of the model name cannot exceed 255 characters!")
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "compatibility", joinColumns = @JoinColumn(name = "model_id"),
//            inverseJoinColumns = @JoinColumn(name = "spare_id"))
//    List<Spare> spares;

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", brand=" + brand +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Model model)) return false;
        return id.equals(model.id) && name.equals(model.name) && category.equals(model.category) && brand.equals(model.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, brand);
    }
}
