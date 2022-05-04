package ru.fixies.fixies.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.fixies.fixies.dtos.ModelDto;
import ru.fixies.fixies.dtos.OrderDto;
import ru.fixies.fixies.services.ModelService;
import ru.fixies.fixies.services.OrderService;


import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final ModelService modelService;
    private final OrderService orderService;

    @GetMapping("/order_page_data")
    public List<ModelDto> findAllModelsForOrderPage() {
        return modelService.findAllModelsForOrderPage().stream().map(ModelDto::new).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDto orderDto, Principal principal) {
        orderService.createOrder(principal, orderDto);
    }


}


