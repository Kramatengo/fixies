package ru.fixies.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RoleDto {

    private String name;

    private Date createdAt;

    private Date updatedAt;
}
