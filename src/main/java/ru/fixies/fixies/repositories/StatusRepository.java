package ru.fixies.fixies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fixies.fixies.model.Status;


@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {


}
