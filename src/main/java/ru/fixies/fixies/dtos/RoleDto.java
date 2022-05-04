package ru.fixies.fixies.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fixies.fixies.model.Role;


@Data
@NoArgsConstructor
public class RoleDto {


    private Long id;
    private String name;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }
}
