package ru.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fixies.model.Role;
import ru.fixies.repositories.RoleRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository rolesRepository;

    public Optional<Role> findById(Long Id) {
        return rolesRepository.findById(Id);
    }

    public Optional<Role> findByRoleName(String rolename) {
        return rolesRepository.findByRoleName(rolename);
    }

    public Role save(Role role) {
        return rolesRepository.save(role);
    }


}
