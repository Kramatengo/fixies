package ru.fixies.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.fixies.dtos.ModelDto;
import ru.fixies.dtos.OrderDto;
import ru.fixies.mapper.ModelMapper;
import ru.fixies.services.ModelService;
import ru.fixies.services.OrderService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final ModelService modelService;
    private final OrderService orderService;

    @GetMapping
    public List<ModelDto> findAllModelsForOrderPage() {
        return modelService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDto orderDto, Principal principal) {
        orderService.createOrder(principal, orderDto);
    }


}


