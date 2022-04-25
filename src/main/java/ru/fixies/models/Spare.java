package ru.fixies.models;

import lombok.*;
import org.hibernate.validator.constraints.Length;
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
@ToString
public class Spare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 255, message = "The length of the spare part name cannot exceed 255 characters!")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "spare")
    @ToString.Exclude
    private List<Stock> stocks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spare spare)) return false;
        return id != null && Objects.equals(id, spare.id) && name.equals(spare.name) && Objects.equals(price, spare.price) &&
                stocks.equals(spare.stocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, stocks);
    }
}
