package ru.fixies.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique=true)
    private String name;

    @OneToMany(
            mappedBy = "brand",
            cascade = CascadeType.ALL)
    private List<Model> models;
}
