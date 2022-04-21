package ru.fixies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fixies.model.Spares;

@Repository
public interface SparesRepository extends JpaRepository<Spares, Long> {


}
