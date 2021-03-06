package ru.fixies.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.fixies.dtos.SpareDto;
import ru.fixies.dtos.*;
import ru.fixies.models.*;

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

    Spare spareDtoToSpare(SpareDto spareDto);
    SpareDto spareToSpareDto(Spare spare);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSpareFromSpareDto(SpareDto spareDto, @MappingTarget Spare spare);

    @AfterMapping
    default void linkStocks(@MappingTarget Spare spare) {
        spare.getStocks().forEach(stock -> stock.setSpare(spare));
    }

    Status statusDtoToStatus(StatusDto statusDto);
    StatusDto statusToStatusDto(Status status);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStatusFromStatusDto(StatusDto statusDto, @MappingTarget Status status);
}
