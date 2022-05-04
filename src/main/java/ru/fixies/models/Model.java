package ru.fixies.models;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "models")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "category_id", nullable = false)
    @ToString.Exclude
    @NotNull
    private Category category;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "brand_id", nullable = false)
    @ToString.Exclude
    @NotNull
    private Brand brand;

    @Length(max = 255, message = "The length of the model name cannot exceed 255 characters!")
    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "compatibility", joinColumns = @JoinColumn(name = "model_id"),
            inverseJoinColumns = @JoinColumn(name = "spare_id"))
    @ToString.Exclude
    List<Spare> spares;

    //TODO: нужны ли нам ордера в моделях?
    @OneToMany(mappedBy = "model")
    @ToString.Exclude
    private List<Order> orders;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Model model)) return false;
        return id != null && Objects.equals(id, model.id) && Objects.equals(name, model.name) && Objects.equals(category, model.category) &&
                Objects.equals(brand, model.brand) && Objects.equals(orders, model.orders) && Objects.equals(spares, model.spares);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, brand, orders, spares);
    }
}
