package ru.fixies.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@ToString
@RequiredArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @ToString.Exclude
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @ToString.Exclude
    @JoinColumn(name = "executor_id", nullable = false)
    private User executor;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @ToString.Exclude
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @Length(max = 255, message = "Subject length cannot exceed 255 characters!")
    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @ToString.Exclude
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    private List<Comment> comments;

    @Length(max = 100, message = "The length of serial number cannot exceed 100 characters!")
    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return id != null && Objects.equals(id, order.id) && Objects.equals(createdAt, order.createdAt) && Objects.equals(customer, order.customer) &&
                Objects.equals(executor, order.executor) && Objects.equals(model, order.model) && Objects.equals(subject, order.subject) &&
                Objects.equals(description, order.description) && Objects.equals(deadline, order.deadline) && Objects.equals(status, order.status) &&
                Objects.equals(comments, order.comments) && Objects.equals(serialNumber, order.serialNumber) && Objects.equals(totalPrice, order.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, customer, executor, model, subject, description, deadline, status, comments,
                serialNumber, totalPrice);
    }
}
