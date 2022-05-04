package ru.fixies.fixies.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fixies.fixies.model.Category;


@Data
@NoArgsConstructor
public class CategoryDto {

    private Long id;
    private String name;
    private String image;
    private String description;
    private String urlPath;
    private String altText;
    private String imgWidth;
    private String imgHeight;

    public CategoryDto(Category categories) {
        this.id = categories.getId();
        this.name = categories.getName();
        this.image = categories.getImage();
        this.description = categories.getDescription();
        this.urlPath = categories.getUrlPath();
        this.altText = categories.getAltText();
        this.imgWidth = categories.getImgWidth();
        this.imgHeight = categories.getImgHeight();
    }
}
