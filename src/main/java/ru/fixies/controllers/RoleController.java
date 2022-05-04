package ru.fixies.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fixies.dtos.RoleDto;
import ru.fixies.services.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleDto> saveRole(@RequestBody RoleDto roleDto) {
        RoleDto save = roleService.save(roleDto);
        RoleDto savedRole = roleService.findByName(save.getName());

        return new ResponseEntity<>(savedRole, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable long id) {
        return new ResponseEntity<>(roleService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteById(@RequestParam long id) {
        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
