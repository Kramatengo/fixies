package ru.fixies.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/all_order_list")
    public List<OrderDto> findAllOrders() {
        return orderService.findAll();
    }


    @GetMapping("order_status/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable long id) {
        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
    }

    @GetMapping("order_edit/{id}")
    public ResponseEntity<OrderDto> getOrderToEditById(@PathVariable long id) {
        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
    }

    @PutMapping("order_edit")
    public void updateOrder(@RequestBody OrderDto orderDto) {
        orderService.updateOrderFromDto(orderDto);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDto orderDto, Principal principal) {
        orderService.createOrder(principal, orderDto);
    }

}


