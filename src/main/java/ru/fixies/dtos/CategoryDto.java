package ru.fixies.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
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
    private String briefDescription;
    private String fullDescription;
}
