package ru.fixies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fixies.models.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    @Query(
            value = "SELECT * " +
                    "FROM categories " +
                    "WHERE id IN (SELECT category_id FROM models WHERE id = :modelId) ",
            nativeQuery = true)
    Optional<Category> findByModelId(Long modelId);
}
