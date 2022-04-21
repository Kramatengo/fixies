package ru.fixies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fixies.model.Brands;
import ru.fixies.model.Statuses;

@Repository
public interface StatusesRepository extends JpaRepository<Statuses, Long> {


}
