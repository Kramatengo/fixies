package ru.fixies.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fixies.dto.RoleDto;
import ru.fixies.dto.UserDto;
import ru.fixies.entity.Role;
import ru.fixies.entity.User;
import ru.fixies.mapper.ModelMapper;
import ru.fixies.repository.UserRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Resource
    UserRepository repository;

    @Transactional
    public UserDto save(UserDto userDto) {
        User user = repository.save(ModelMapper.INSTANCE.dtoToUser(userDto));
        return ModelMapper.INSTANCE.userToDto(user);
    }

    @Transactional(readOnly = true)
    public UserDto findById(long id) {
        Optional<User> user = repository.findById(id);
        return user.map(ModelMapper.INSTANCE::userToDto).orElse(null);
    }

    @Transactional(readOnly = true)
    public UserDto findByLogin(String login) {
        Optional<User> user = repository.findByLogin(login);
        return user.map(ModelMapper.INSTANCE::userToDto).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        List<User> all = repository.findAll();
        return all.stream().map(ModelMapper.INSTANCE::userToDto).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
