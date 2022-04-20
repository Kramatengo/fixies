package ru.fixies.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fixies.dto.ModelDto;
import ru.fixies.service.ModelService;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ModelController {

    @Resource
    private ModelService modelService;

    @PostMapping("/model")
    public ResponseEntity<ModelDto> saveModel(@RequestBody ModelDto modelDto) {
        ModelDto save = modelService.save(modelDto);

        ModelDto savedModel = modelService.findByName(save.getName());
        return new ResponseEntity<>(savedModel, HttpStatus.OK);
    }

    @GetMapping("/models")
    public ResponseEntity<List<ModelDto>> getAllModels() {
        return new ResponseEntity<>(modelService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/model")
    public ResponseEntity<ModelDto> getModelById(@RequestParam long id) {
        return new ResponseEntity<>(modelService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/model")
    public ResponseEntity<HttpStatus> deleteById(@RequestParam long id) {
        modelService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
