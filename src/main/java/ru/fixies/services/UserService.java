package ru.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fixies.model.Roles;
import ru.fixies.model.Users;
import ru.fixies.repositories.UserRolesRepository;
import ru.fixies.repositories.UsersRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

private final UsersRepository usersRepository;
private final UserRolesRepository userRolesRepository;

    public Optional<Users> findByLogin(String login) {
        return usersRepository.findByLogin(login);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Users users = findByLogin(login).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", login)));
        return new org.springframework.security.core.userdetails.User(users.getLogin(), users.getPassword(), mapRolesToAuthorities(users.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Roles> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public Users save(Users user) {
        return usersRepository.save(user);
    }




}
