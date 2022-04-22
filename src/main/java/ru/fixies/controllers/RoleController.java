package ru.fixies.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fixies.dtos.RoleDto;
import ru.fixies.services.RoleService;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class RoleController {

    @Resource
    private RoleService roleService;

    @PostMapping("/role")
    public ResponseEntity<RoleDto> saveRole(@RequestBody RoleDto roleDto) {
        RoleDto save = roleService.save(roleDto);

        RoleDto savedRole = roleService.findByName(save.getName());
        return new ResponseEntity<>(savedRole, HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/role")
    public ResponseEntity<RoleDto> getRoleById(@RequestParam long id) {
        return new ResponseEntity<>(roleService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/role")
    public ResponseEntity<HttpStatus> deleteById(@RequestParam long id) {
        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
