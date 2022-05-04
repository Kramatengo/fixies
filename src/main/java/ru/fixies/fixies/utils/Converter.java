package ru.fixies.fixies.utils;


import org.springframework.stereotype.Component;
import ru.fixies.fixies.dtos.ModelDto;
import ru.fixies.fixies.model.Model;


@Component
public class Converter {

    public ModelDto modelToDto(Model model) {
        return new ModelDto(model.getId(),
                model.getCategory().getDescription(),
                model.getBrand().getName(),
                model.getName()
        );
    }


}
