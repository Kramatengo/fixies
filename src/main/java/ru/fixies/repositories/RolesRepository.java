package ru.fixies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fixies.model.Roles;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {


    @Query("select c from Roles c where c.name = :name")
    Optional<Roles> findByRoleName(String name);


}
