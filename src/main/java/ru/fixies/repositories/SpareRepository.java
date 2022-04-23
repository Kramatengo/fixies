package ru.fixies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fixies.models.Spare;

@Repository
public interface SpareRepository extends JpaRepository<Spare, Long> {


}
