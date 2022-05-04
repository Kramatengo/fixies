package ru.fixies.fixies.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name="image")
    private String image;

    @Column(name="description")
    private String description;

    @Column(name="url_path")
    private String urlPath;

    @Column(name="alt_text")
    private String altText;

    @Column(name="img_width")
    private String imgWidth;

    @Column(name="img_height")
    private String imgHeight;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private List<Model> modelCategory;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private List<Order> orderCategory;

}
