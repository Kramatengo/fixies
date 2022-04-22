package ru.fixies.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fixies.dtos.ModelDto;
import ru.fixies.services.ModelService;

@RestController
@RequestMapping("/api/v1/models")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelsService;

    @GetMapping
    public Page<ModelDto> findAllModels(@RequestParam(defaultValue = "1", name = "p") int pageIndex){
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return modelsService.findAllModels(pageIndex - 1, 20).map(ModelDto::new);
    }



}
