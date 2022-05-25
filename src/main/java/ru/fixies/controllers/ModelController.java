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

        ModelDto savedModel = ModelMapper.INSTANCE.modelToDto(modelService.findByName(save.getName()));
        return new ResponseEntity<>(savedModel, HttpStatus.CREATED);
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
    public ResponseEntity<List<ModelDto>>  findModelsBySelectedBrandAndCategory(
            @PathVariable ("selectedBrandId") long selectedBrandId,
            @PathVariable ("selectedCategoryId") Long selectedCategoryId) {
        return new ResponseEntity<>(modelService.findByBrandIdAndCategoryId(selectedBrandId, selectedCategoryId), HttpStatus.OK);
    }

    @GetMapping("/by_category/{selectedCategoryId}")
    public ResponseEntity<List<ModelDto>> findModelsBySelectedCategory(@PathVariable long selectedCategoryId) {
        return new ResponseEntity<>(modelService.findByCategoryId(selectedCategoryId), HttpStatus.OK);
    }

    @GetMapping("/by_brand/{selectedBrandId}")
    public ResponseEntity<List<ModelDto>> findModelsBySelectedBrand(@PathVariable long selectedBrandId) {
        return new ResponseEntity<>(modelService.findByBrandId(selectedBrandId), HttpStatus.OK);
    }

}
