package ru.fixies.fixies.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fixies.fixies.model.Brand;


@Data
@NoArgsConstructor
public class BrandDto {

    private Long id;
    private String name;

    public BrandDto(Brand brand) {
        this.id = brand.getId();
        this.name = brand.getName();
    }
}
