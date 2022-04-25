package ru.fixies.models;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "stock")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "spare_id")
    @ToString.Exclude
    private Spare spare;

    @Column(name = "quantity")
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Stock stock = (Stock) o;
        return id != null && Objects.equals(id, stock.id) && Objects.equals(spare, stock.spare) && Objects.equals(quantity, stock.quantity);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
