package ru.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fixies.model.Roles;
import ru.fixies.repositories.RolesRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

private final RolesRepository rolesRepository;

    public Optional<Roles> findById(Long Id) {
        return rolesRepository.findById(Id);
    }

    public Optional<Roles> findByRoleName(String rolename) {
        return rolesRepository.findByRoleName(rolename);
    }

    public Roles save(Roles roles) {
        return rolesRepository.save(roles);
    }



}
