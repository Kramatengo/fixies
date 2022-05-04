package ru.fixies.models;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "statuses")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Length(max = 255, message = "The length of the status name cannot exceed 255 characters!")
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "status")
    @ToString.Exclude
    private List<Order> orders;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return id != null && Objects.equals(id, status.id) && Objects.equals(name, status.name) && Objects.equals(orders, status.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, orders);
    }
}
