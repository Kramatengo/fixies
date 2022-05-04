package ru.fixies.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusDto implements Serializable {
    private Long id;
    @Length(max = 255, message = "The length of the status name cannot exceed 255 characters!")
    private String name;
}
