package ru.fixies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fixies.models.UserRoles;

import java.util.Optional;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

    Optional<UserRoles> findByUserId(Long userId);


}
