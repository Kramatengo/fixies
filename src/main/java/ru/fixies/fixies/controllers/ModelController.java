package ru.fixies.fixies.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.fixies.fixies.dtos.ModelDto;
import ru.fixies.fixies.dtos.ModelOrderSelectDto;
import ru.fixies.fixies.services.ModelService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/models")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;

    @GetMapping("/all_models_pageable")
    public Page<ModelDto> findAllModelsPageable(@RequestParam(defaultValue = "1", name = "p") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return modelService.findAllModelsPageable(pageIndex - 1, 20).map(ModelDto::new);
    }

//    @GetMapping("/all_models_for_order_page/{selectedBrandId}")
//    public List<ModelDto> findModelsBySelectedBrandForOrderPage(Long selectedBrandId) {
//        return modelService.findModelsBySelectedBrandForOrderPage(selectedBrandId).stream().map(ModelDto::new).collect(Collectors.toList());
//
//    }

//    @GetMapping("/models_for_by_selected_category/{selectedBrandId}")
//    public List<ModelDto> findModelsBySelectedBrandForOrderPage(Long selectedBrandId) {
//        return modelService.findModelsBySelectedBrandForOrderPage(selectedBrandId).stream().map(ModelDto::new).collect(Collectors.toList());
//
//    }

    @GetMapping("/brands_by_selected_category/{selectedCategoryId}")
    public List<ModelDto> findBrandsBySelectedCategory(@PathVariable Long selectedCategoryId) {
        System.out.println("selectedCategoryId = " + selectedCategoryId);
        return modelService.findBrandsBySelectedCategory(selectedCategoryId).stream().map(ModelDto::new).collect(Collectors.toList());

    }

    @GetMapping("/models_by_selected_brand/{selectedBrandId}/{selectedCategoryId}")
    public List<ModelDto>  findModelsBySelectedBrand(@PathVariable ("selectedBrand") Long selectedBrandId, @PathVariable ("selectedCategory") Long selectedCategoryId) {
        System.out.println("selectedBrandId = " + selectedBrandId);

        return modelService.findModelsBySelectedBrand(selectedBrandId, selectedCategoryId).stream().map(ModelDto::new).collect(Collectors.toList());
    }

//    @GetMapping("/all_models_for_order_page")
//    public List<ModelOrderSelectDto>  findAllModelsForOrderPage() {
//
//        return modelService.findAllModelsForOrderPage().stream().map(ModelOrderSelectDto::new).collect(Collectors.toList());
//    }

    @GetMapping("/all_models_for_order_page")
    public List<ModelOrderSelectDto>  findAll() {

        return modelService.findAll().stream().map(ModelOrderSelectDto::new).collect(Collectors.toList());
    }




}
