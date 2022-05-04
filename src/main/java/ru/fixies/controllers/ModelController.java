package ru.fixies.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fixies.dtos.ModelDto;
import ru.fixies.mapper.ModelMapper;
import ru.fixies.services.ModelService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/models")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;

    @GetMapping
    public Page<ModelDto> getAllModels(@RequestParam(defaultValue = "1", name = "p") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return modelService.findAllModels(pageIndex - 1, 20).map(ModelMapper.INSTANCE::modelToDto);
    }

    @PostMapping
    public ResponseEntity<ModelDto> saveModel(@RequestBody ModelDto modelDto) {
        ModelDto save = modelService.save(modelDto);

        ModelDto savedModel = modelService.findByName(save.getName());
        return new ResponseEntity<>(savedModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelDto> getModelById(@PathVariable long id) {
        return new ResponseEntity<>(modelService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable long id) {
        modelService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{selectedBrandId}/{selectedCategoryId}")
    public List<ru.fixies.dtos.ModelDto>  findModelsBySelectedBrand(@PathVariable ("selectedBrand") long selectedBrandId, @PathVariable ("selectedCategory") Long selectedCategoryId) {
        return Collections.emptyList();
    }

    @GetMapping("/by_category/{selectedCategoryId}")
    public List<ru.fixies.dtos.ModelDto> findBrandsBySelectedCategory(@PathVariable long selectedCategoryId) {
        return Collections.emptyList();

    }
}
