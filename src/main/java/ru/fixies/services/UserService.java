package ru.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fixies.dtos.UserDto;
import ru.fixies.mapper.ModelMapper;
import ru.fixies.models.Role;
import ru.fixies.models.User;
import ru.fixies.repositories.UserRepository;
import ru.fixies.repositories.UserRolesRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;

    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    //TODO: Нельзя оставить имя метода findByLogin, поэтому добавил 2. Необходимо определиться нужен этот метод или нет.
    @Transactional(readOnly = true)
    public UserDto findByLogin2(String login) {
        Optional<User> user = userRepository.findByLogin(login);
        return user.map(ModelMapper.INSTANCE::userToDto).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", login)));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = findByLogin(login).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", login)));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Transactional
    public UserDto save(ru.fixies.dtos.UserDto userDto) {
        User user = userRepository.save(ModelMapper.INSTANCE.dtoToUser(userDto));
        return ModelMapper.INSTANCE.userToDto(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserDto findById(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ModelMapper.INSTANCE::userToDto).orElse(null);
    }


    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        List<User> all = userRepository.findAll();
        return all.stream().map(ModelMapper.INSTANCE::userToDto).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
