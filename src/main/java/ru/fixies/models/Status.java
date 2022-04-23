package ru.fixies.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "statuses")
@Getter
@Setter
@NoArgsConstructor
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Range(max = 255, message = "The length of the status name cannot exceed 255 characters!")
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "status")
    private List<Order> orders;

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Status status)) return false;
        return id.equals(status.id) && name.equals(status.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
