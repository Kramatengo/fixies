package ru.fixies.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "spares")
@Getter
@Setter
@NoArgsConstructor
public class Spare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Range(max = 255, message = "The length of the spare part name cannot exceed 255 characters!")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "spare")
    private List<Stock> stocks;

    @Override
    public String toString() {
        return "Spare{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spare spare)) return false;
        return id.equals(spare.id) && name.equals(spare.name) && Objects.equals(price, spare.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }
}
