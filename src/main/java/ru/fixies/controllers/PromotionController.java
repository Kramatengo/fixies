package ru.fixies.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fixies.dtos.CategoryDto;
import ru.fixies.services.CategoryService;

@RestController
@RequestMapping("/api/v1/promotion")
@RequiredArgsConstructor
public class PromotionController {

    private final CategoryService categoryService;


    @GetMapping("promo_page/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable long id) {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }


}
