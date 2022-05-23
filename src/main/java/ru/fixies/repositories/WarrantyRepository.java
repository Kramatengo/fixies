package ru.fixies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fixies.models.Warranty;

public interface WarrantyRepository extends JpaRepository<Warranty, Long> {

    Warranty findByName(String name);


}
