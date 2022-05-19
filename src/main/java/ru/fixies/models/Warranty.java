package ru.fixies.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "warranty")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Warranty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Length(max = 255, message = "The length of the warranty name cannot exceed 255 characters!")
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "warranty")
    @ToString.Exclude
    private List<Order> orders;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warranty warranty = (Warranty) o;
        return id != null && Objects.equals(id, warranty.id) && Objects.equals(name, warranty.name) && Objects.equals(orders, warranty.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, orders);
    }
}


