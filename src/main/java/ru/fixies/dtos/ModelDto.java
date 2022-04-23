package ru.fixies.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
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

    public ModelDto(Model model) {
        this.id = model.getId();
        this.category = model.getCategory();
        this.brand = model.getBrand();
        this.name = getName();
    }

}
