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
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Model {
    @ManyToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinTable(name = "compatibility", joinColumns = @JoinColumn(name = "model_id"),
            inverseJoinColumns = @JoinColumn(name = "spare_id"))
    List<Spare> spares;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length(max = 255, message = "The length of the model name cannot exceed 255 characters!")
    @Column(name = "name", nullable = false)
    @NotNull
    private String name;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @ToString.Exclude
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull
    private Category category;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @ToString.Exclude
    @JoinColumn(name = "brand_id", nullable = false)
    @NotNull
    private Brand brand;
    //TODO: нужны ли нам ордера в моделях?
    @OneToMany(mappedBy = "model")
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
