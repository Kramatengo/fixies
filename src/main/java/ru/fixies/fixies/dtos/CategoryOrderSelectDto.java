package ru.fixies.fixies.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fixies.fixies.model.Category;


@Data
@NoArgsConstructor
public class CategoryOrderSelectDto {

    private Long id;
    private String name;


    public CategoryOrderSelectDto(Category categories) {
        this.id = categories.getId();
        this.name = categories.getName();

    }
}
