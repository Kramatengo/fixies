package ru.fixies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fixies.entity.Brand;
import ru.fixies.entity.Category;
import ru.fixies.entity.Model;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    Optional<Model> findByName(String name);

    Optional<List<Model>> findByBrand(Brand brand);

    Optional<List<Model>> findByCategory(Category category);

    Optional<List<Model>> findByBrandAndCategory(Brand brand, Category category);

}
