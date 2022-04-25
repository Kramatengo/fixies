package ru.fixies.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ru.fixies.models.Brand;
import ru.fixies.models.Category;
import ru.fixies.models.Model;

@Data
@NoArgsConstructor
public class ModelDto {

    private Long id;
    private Category category;
    private Brand brand;
    private String name;

}
