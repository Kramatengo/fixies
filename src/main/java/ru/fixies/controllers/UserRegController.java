package ru.fixies.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fixies.dtos.UserDto;
import ru.fixies.exceptions.DataValidationException;
import ru.fixies.models.Role;
import ru.fixies.models.User;
import ru.fixies.models.UserRoles;
import ru.fixies.services.RoleService;
import ru.fixies.services.UserRolesService;
import ru.fixies.services.UserService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/reg_user")
@RequiredArgsConstructor
public class UserRegController {

    private final String INITIAL_USER_ROLE = "ROLE_USER";

    private final UserService userService;
    private final RoleService roleService;
    private final UserRolesService userRolesService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping
//    public String save(@RequestBody @Validated UserDto userRegRequest, BindingResult bindingResult) {
    public void save(@RequestBody @Validated UserDto userRegRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }

        User user = new User();
        user.setLogin(userRegRequest.getLogin());
        user.setFirstName(userRegRequest.getFirstName());
        user.setLastName(userRegRequest.getLastName());
        user.setMiddleNames(userRegRequest.getMiddleNames());
        user.setEmail(userRegRequest.getEmail());
        user.setPhone(userRegRequest.getPhone());
        user.setPassword(bCryptPasswordEncoder.encode(userRegRequest.getPassword()));
        userService.save(user);

        UserRoles userRole = new UserRoles();
        userRole.setUserId(user.getId());
        userRole.setRoleId(roleService.findByRoleName(INITIAL_USER_ROLE).stream().map(Role::getId).toList().get(0));
        userRolesService.save(userRole);

//        return "SUCCESS";
    }
}
