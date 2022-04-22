package ru.fixies.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private String middleName;

    private String email;

    private String phone;

    private Date createdAt;

    private Date updatedAt;

    private List<RoleDto> roles;
}
