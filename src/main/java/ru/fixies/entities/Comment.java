package ru.fixies.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "text")
    private String text;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", order=" + order +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment comment)) return false;
        return id.equals(comment.id) && order.equals(comment.order) && Objects.equals(text, comment.text) && Objects.equals(createdAt, comment.createdAt) && user.equals(comment.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, text, createdAt, user);
    }
}
