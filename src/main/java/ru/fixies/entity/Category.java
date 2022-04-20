package ru.fixies.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique=true)
    private String name;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL)
    private List<Model> models;
}
