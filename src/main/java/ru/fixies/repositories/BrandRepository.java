package ru.fixies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fixies.models.Brand;
import ru.fixies.models.Model;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findByName(String name);

    @Query(
            value = "SELECT * " +
                    "FROM brands " +
                    "WHERE id IN (SELECT brand_id FROM models WHERE id = :modelId) ",
            nativeQuery = true)
    Optional<Brand> findByModelId(Long modelId);

}
