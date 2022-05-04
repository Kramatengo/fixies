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

    @Transactional
    public void createOrder(Principal principal, OrderDto orderDto) {

        User user = userService.findByLogin(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти пользователя при оформлении заказа. Имя пользователя: " + principal.getName()));
        Order order = new Order();
        order.setCustomer(user);   //OK
        order.setExecutor(ModelMapper.INSTANCE.dtoToUser(orderDto.getExecutor()));  // OK
        order.setModel(ModelMapper.INSTANCE.dtoToModel(orderDto.getModel()));  // NOK

        order.setSubject(orderDto.getSubject());   //OK
        order.setDescription(orderDto.getDescription());   //OK
        order.setDeadline(orderDto.getDeadline());  //OK
        order.setStatus(ModelMapper.INSTANCE.statusDtoToStatus(orderDto.getStatus()));    // OK
        order.setSerialNumber(orderDto.getSerialNumber());  // OK
        order.setTotalPrice(orderDto.getTotalPrice());  // OK

        orderRepository.save(order);
    }
}
