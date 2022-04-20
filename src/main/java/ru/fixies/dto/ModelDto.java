package ru.fixies.dto;

import lombok.Data;

@Data
public class ModelDto {

    private String name;

    private CategoryDto category;

    private BrandDto brand;
}
