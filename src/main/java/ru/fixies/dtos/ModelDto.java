package ru.fixies.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fixies.models.Brand;
import ru.fixies.models.Category;

@Data
@NoArgsConstructor
public class ModelDto {

    private Long id;
    private Category category;
    private Brand brand;
    private String name;

}
