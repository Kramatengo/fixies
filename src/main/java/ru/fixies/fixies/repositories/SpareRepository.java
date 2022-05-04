package ru.fixies.fixies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fixies.fixies.model.Spare;

@Repository
public interface SpareRepository extends JpaRepository<Spare, Long> {


}
