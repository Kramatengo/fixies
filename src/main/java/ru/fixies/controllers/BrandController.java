package ru.fixies.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fixies.dtos.BrandDto;
import ru.fixies.services.BrandService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<BrandDto> saveBrand(@RequestBody BrandDto brandDto) {
        BrandDto save = brandService.save(brandDto);

        BrandDto savedBrand = brandService.findByName(save.getName());
        return new ResponseEntity<>(savedBrand, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BrandDto>> getAllBrands() {
        return new ResponseEntity<>(brandService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDto> getBrandById(@PathVariable long id) {
        return new ResponseEntity<>(brandService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable long id) {
        brandService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/model/{id}")
    public ResponseEntity<BrandDto> getBrandByModelId(@PathVariable long id) {
        return new ResponseEntity<>(brandService.findByModelId(id), HttpStatus.OK);
    }
}
