package ru.fixies.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;

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
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "executor_id", nullable = false)
    private User executor;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @Range(max = 255, message = "Subject length cannot exceed 255 characters!")
    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "order")
    private List<Comment> comments;

    @Range(max = 100, message = "The length of serial number cannot exceed 100 characters!")
    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", customer=" + customer +
                ", executor=" + executor +
                ", model=" + model +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", deadline=" + deadline +
                ", status=" + status +
                ", comments=" + comments +
                ", serialNumber='" + serialNumber + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return id.equals(order.id) && Objects.equals(createdAt, order.createdAt) && customer.equals(order.customer) && executor.equals(order.executor) &&
                model.equals(order.model) && subject.equals(order.subject) && text.equals(order.text) && Objects.equals(deadline, order.deadline) &&
                status.equals(order.status) && Objects.equals(serialNumber, order.serialNumber) && Objects.equals(totalPrice, order.totalPrice) &&
                Objects.equals(comments, order.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, customer, executor, model, subject, text, deadline, status, serialNumber, totalPrice, comments);
    }
}
