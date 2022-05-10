package ru.fixies.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fixies.models.Brand;
import ru.fixies.models.Category;
import ru.fixies.models.Model;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    @Query(value = "SELECT models.id, models.category_id, " +
                    "models.brand_id, models.name " +
                    "FROM models " +
                    "ORDER BY category_id DESC ",
            // add countQuery = "SELECT count(*) FROM tbl_cs_models_new" to ORDER BY id
            countQuery = "SELECT count(*) FROM models",
            nativeQuery = true)
    Page<Model> findAllModels(Pageable pageable);

    Optional<Model> findByName(String name);

    Optional<List<Model>> findByBrand(Brand brand);

    Optional<List<Model>> findByCategory(Category category);

    Optional<List<Model>> findByBrandAndCategory(Brand brand, Category category);

    Optional<List<Model>> findByBrandIdAndCategoryId(Long brandId, Long categoryId);

    Optional<List<Model>> findByBrandId(Long brandId);

    Optional<List<Model>> findByCategoryId(Long categoryId);

}
