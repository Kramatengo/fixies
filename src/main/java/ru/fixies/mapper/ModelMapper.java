package ru.fixies.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.fixies.dtos.*;
import ru.fixies.model.*;

@Mapper
public interface ModelMapper {

    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

    Brand dtoToBrand(BrandDto brandDto);

    BrandDto brandToDto(Brand brand);

    Category dtoToCategory(CategoryDto categoryDto);

    CategoryDto categoryToDto(Category category);

    Model dtoToModel(ModelDto modelDto);

    ModelDto modelToDto(Model model);

    User dtoToUser(UserDto userDto);

    UserDto userToDto(User user);

    Role dtoToRole(RoleDto roleDto);

    RoleDto roleToDto(Role role);
}
