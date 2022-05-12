package ru.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fixies.dtos.ModelDto;
import ru.fixies.dtos.OrderDto;
import ru.fixies.dtos.UserDto;
import ru.fixies.exceptions.ResourceNotFoundException;
import ru.fixies.mapper.ModelMapper;
import ru.fixies.models.*;
import ru.fixies.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    public final OrderRepository orderRepository;
    private final UserService userService;
    private final ModelService modelService;
    private final StatusService statusService;
//    private final UserDto userDto;

    @Transactional
    public void createOrder(Principal principal, OrderDto orderDto) {

        User user = userService.findByLogin(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти пользователя при оформлении заказа. Имя пользователя: " + principal.getName()));
        Order order = new Order();
        order.setCustomer(user);
        order.setExecutor(userService.findByLogin(orderDto.getExecutor().getLogin()).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти пользователя при оформлении заказа. Имя пользователя: " + orderDto.getExecutor().getLogin())));
        order.setModel(modelService.findByName(orderDto.getModel().getName()));

        order.setSubject(orderDto.getSubject());
        order.setDescription(orderDto.getDescription());
        order.setDeadline(orderDto.getDeadline());
        order.setStatus(statusService.findByName(orderDto.getStatus().getName()));
        order.setSerialNumber(orderDto.getSerialNumber());
        order.setTotalPrice(orderDto.getTotalPrice());

        orderRepository.save(order);
    }

    @Transactional
    public void updateOrderFromDto(OrderDto orderDto) {
        Order order = orderRepository.findById(orderDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Order id = " + orderDto.getId() + " not found"));

        order.setDeadline(orderDto.getDeadline());
        order.setSubject(orderDto.getSubject());
        order.setSerialNumber(orderDto.getSerialNumber());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setDescription(orderDto.getDescription());
        Status status = statusService.findByName(orderDto.getStatus().getName());
        order.setStatus(status);
        Model model = modelService.findByName(orderDto.getModel().getName());
        order.setModel(model);
    }

        @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<OrderDto> findAll() {
        List<Order> all = orderRepository.findAll();
        return all.stream().map(ModelMapper.INSTANCE::orderToDto).collect(Collectors.toList());
    }

//    @org.springframework.transaction.annotation.Transactional(readOnly = true)
//    public Optional<OrderDto> findOrderById(Long orderId) {
//        Optional<Order> orderById = orderRepository.findById(orderId);
////        return orderById.stream().map(ModelMapper.INSTANCE::orderToDto).collect(Collectors.toList());
//        return orderById(orderId);
//
//    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public OrderDto findById(long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(ModelMapper.INSTANCE::orderToDto).orElse(null);

    }


}
