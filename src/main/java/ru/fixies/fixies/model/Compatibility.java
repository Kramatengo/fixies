package ru.fixies.fixies.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compatibility")
@Data
@NoArgsConstructor
public class Compatibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="model_id")
    private Long modelId;

    @Column (name="spare_id")
    private Long spareId;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
