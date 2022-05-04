package ru.fixies.fixies.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fixies.fixies.model.Status;


@Data
@NoArgsConstructor
public class StatusDto {

    private Long id;
    private String name;

    public StatusDto(Status status) {
        this.id = status.getId();
        this.name = status.getName();
    }
}
