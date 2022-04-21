package ru.fixies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fixies.model.Brands;

@Repository
public interface BrandsRepository extends JpaRepository<Brands, Long> {


}
