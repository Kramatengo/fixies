package ru.fixies.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ModelDto {

    private Long id;
    private CategoryDto category;
    private BrandDto brand;
    private String name;

}
