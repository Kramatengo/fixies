package ru.fixies.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fixies.fixies.model.UserRole;
import ru.fixies.fixies.repositories.UserRoleRepository;


@Service
@RequiredArgsConstructor
public class UserRoleService {

    public final UserRoleRepository userRoleRepository;

    public UserRole save(UserRole userRoles) {
        return userRoleRepository.save(userRoles);
    }

}
