package ru.fixies.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fixies.model.Brands;
import ru.fixies.model.Categories;
import ru.fixies.model.Models;

@Data
@NoArgsConstructor
public class ModelsDto {

        private Long id;
        private Categories categories;
        private Brands brand;
        private String name;

    public ModelsDto(Models models) {
        this.id = models.getId();
        this.categories = models.getCategories();
        this.brand =models.getBrand();
        this.name = getName();
    }
}
