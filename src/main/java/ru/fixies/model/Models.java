package ru.fixies.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "models")
@Data
@NoArgsConstructor
public class Models {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories categories;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brands brand;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "compatibility",
            joinColumns = @JoinColumn(name = "model_id"),
            inverseJoinColumns = @JoinColumn(name = "spare_id"))
    private Collection<Spares> spares;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "model")
    private List<Orders> ordersModels;

}
