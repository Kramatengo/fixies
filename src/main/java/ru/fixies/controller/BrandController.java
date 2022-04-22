package ru.fixies.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fixies.dto.BrandDto;
import ru.fixies.service.BrandService;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BrandController {

    @Resource
    private BrandService brandService;

    @PostMapping("/brand")
    public ResponseEntity<BrandDto> saveBrand(@RequestBody BrandDto brandDto) {
        BrandDto save = brandService.save(brandDto);

        BrandDto savedBrand = brandService.findByName(save.getName());
        return new ResponseEntity<>(savedBrand, HttpStatus.OK);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<BrandDto>> getAllBrands() {
        return new ResponseEntity<>(brandService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/brand")
    public ResponseEntity<BrandDto> getBrandById(@RequestParam long id) {
        return new ResponseEntity<>(brandService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/brand")
    public ResponseEntity<HttpStatus> deleteById(@RequestParam long id) {
        brandService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
