package ru.fixies.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fixies.dto.CategoryDto;
import ru.fixies.service.CategoryService;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto save = categoryService.save(categoryDto);

        CategoryDto saveCategory = categoryService.findByName(save.getName());
        return new ResponseEntity<>(saveCategory, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<CategoryDto> getCategoryById(@RequestParam long id) {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/category")
    public ResponseEntity<HttpStatus> deleteById(@RequestParam long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
