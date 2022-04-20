package ru.fixies.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fixies.dto.BrandDto;
import ru.fixies.dto.RoleDto;
import ru.fixies.service.BrandService;
import ru.fixies.service.RoleService;

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
