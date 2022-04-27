package ru.fixies.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
public class StatusDto implements Serializable {
    private final Long id;
    @Length(max = 255, message = "The length of the status name cannot exceed 255 characters!")
    private final String name;
}
