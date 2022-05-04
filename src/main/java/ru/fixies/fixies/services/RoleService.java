package ru.fixies.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fixies.fixies.model.Role;
import ru.fixies.fixies.repositories.RoleRepository;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Optional<Role> findById(Long Id) {
        return roleRepository.findById(Id);
    }

    public Optional<Role> findByRoleName(String rolename) {
        return roleRepository.findByRoleName(rolename);
    }

    public Role save(Role roles) {
        return roleRepository.save(roles);
    }


}
