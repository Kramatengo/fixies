package ru.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fixies.dtos.OrderDto;
import ru.fixies.exceptions.ResourceNotFoundException;
import ru.fixies.mapper.ModelMapper;
import ru.fixies.models.Order;
import ru.fixies.models.User;
import ru.fixies.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.security.Principal;

@Service
@RequiredArgsConstructor
public class OrderService {

    public final OrderRepository orderRepository;
    private final UserService userService;
    private final ModelService modelService;
    private final StatusService statusService;

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
}
