package ru.fixies.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fixies.model.Models;

@Repository
public interface ModelsRepository  extends JpaRepository<Models, Long> {

    @Query(
            value = "SELECT models.id, models.category_id, " +
                    "models.brand_id, models.name " +
                    "FROM models " +
                    "ORDER BY category_id DESC ",
            // add countQuery = "SELECT count(*) FROM tbl_cs_models_new" to ORDER BY id
            countQuery = "SELECT count(*) FROM models",
            nativeQuery = true)
    Page<Models> findAllModels(Pageable pageable);

}
