package ru.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fixies.model.UserRoles;
import ru.fixies.repositories.UserRolesRepository;

@Service
@RequiredArgsConstructor
public class UserRolesService {

    public final UserRolesRepository userRolesRepository;

    public UserRoles save(UserRoles userRoles) {
        return userRolesRepository.save(userRoles);
    }

}
