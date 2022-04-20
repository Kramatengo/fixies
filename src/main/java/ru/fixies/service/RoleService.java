package ru.fixies.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fixies.dto.BrandDto;
import ru.fixies.dto.RoleDto;
import ru.fixies.entity.Brand;
import ru.fixies.entity.Role;
import ru.fixies.mapper.ModelMapper;
import ru.fixies.repository.BrandRepository;
import ru.fixies.repository.RoleRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Resource
    private RoleRepository repository;

    @Transactional
    public RoleDto save(RoleDto roleDto) {
        Role role = repository.save(ModelMapper.INSTANCE.dtoToRole(roleDto));
        return ModelMapper.INSTANCE.roleToDto(role);
    }

    @Transactional(readOnly = true)
    public RoleDto findById(long id) {
        Optional<Role> role = repository.findById(id);
        return role.map(ModelMapper.INSTANCE::roleToDto).orElse(null);
    }

    @Transactional(readOnly = true)
    public RoleDto findByName(String name) {
        Optional<Role> role = repository.findByName(name);
        return role.map(ModelMapper.INSTANCE::roleToDto).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<RoleDto> findAll() {
        List<Role> all = repository.findAll();
        return all.stream().map(ModelMapper.INSTANCE::roleToDto).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
