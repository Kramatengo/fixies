package ru.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fixies.dtos.RoleDto;
import ru.fixies.mapper.ModelMapper;
import ru.fixies.models.Role;
import ru.fixies.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository rolesRepository;

    @Transactional
    public RoleDto save(RoleDto roleDto) {
        Role role = rolesRepository.save(ModelMapper.INSTANCE.dtoToRole(roleDto));
        return ModelMapper.INSTANCE.roleToDto(role);
    }

    public Role save(Role role) {
        return rolesRepository.save(role);
    }

    @Transactional(readOnly = true)
    public RoleDto findById(long id) {
        Optional<Role> role = rolesRepository.findById(id);
        return role.map(ModelMapper.INSTANCE::roleToDto).orElse(null);
    }

    public Optional<Role> findById(Long Id) {
        return rolesRepository.findById(Id);
    }

    @Transactional(readOnly = true)
    public RoleDto findByName(String name) {
        Optional<Role> role = rolesRepository.findByName(name);
        return role.map(ModelMapper.INSTANCE::roleToDto).orElse(null);
    }

    public Optional<Role> findByRoleName(String roleName) {
        return rolesRepository.findByRoleName(roleName);
    }

    @Transactional(readOnly = true)
    public List<RoleDto> findAll() {
        List<Role> all = rolesRepository.findAll();
        return all.stream().map(ModelMapper.INSTANCE::roleToDto).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        rolesRepository.deleteById(id);
    }
}
