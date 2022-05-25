package ru.fixies.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fixies.dtos.WarrantyDto;
import ru.fixies.mapper.ModelMapper;
import ru.fixies.services.WarrantyService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/warranties")
@RequiredArgsConstructor
public class WarrantyController {

    private final WarrantyService warrantyService;

    @GetMapping
    public List<WarrantyDto> findAllWarrantiesForOrderPage() {
        return warrantyService.findAll().stream().map(ModelMapper.INSTANCE::warrantyToWarrantyDto).collect(Collectors.toList());
    }

}
