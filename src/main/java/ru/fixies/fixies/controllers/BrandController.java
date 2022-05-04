package ru.fixies.fixies.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fixies.fixies.dtos.BrandDto;
import ru.fixies.fixies.dtos.BrandOrderSelectDto;
import ru.fixies.fixies.services.BrandService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/all_brands_pageable")
    public Page<BrandDto> findAllBrandsPageable(@RequestParam(defaultValue = "1", name = "p") int pageIndex){
        if (pageIndex < 1) {
            pageIndex = 1;
        }

        return brandService.findAllBrandsPageable(pageIndex - 1, 50).map(BrandDto::new);
    }



    @GetMapping("/all_brands_for_order_page")
    public List<BrandOrderSelectDto> findAllBrandsForOrderPage(){

        return brandService.findAllBrandsForOrderPage().stream().map(BrandOrderSelectDto::new).collect(Collectors.toList());
    }


}
