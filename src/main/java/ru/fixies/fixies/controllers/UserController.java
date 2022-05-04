package ru.fixies.fixies.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fixies.fixies.dtos.UserDto;
import ru.fixies.fixies.services.UserService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all_users_for_order_page")
    public List<UserDto> findAllUsersForOrderForm() {
        return userService.findAllUsersForOrderForm().stream().map(UserDto::new).collect(Collectors.toList());
    }




}
