package ru.fixies.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fixies.fixies.dtos.OrderDto;
import ru.fixies.fixies.exceptions.ResourceNotFoundException;
import ru.fixies.fixies.model.Order;
import ru.fixies.fixies.model.User;
import ru.fixies.fixies.repositories.OrderRepository;

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

        order.setApplicantName(orderDto.getApplicantName());  //OK
        order.setApplicantPhone(orderDto.getApplicantPhone());  //OK
        order.setApplicantEmail(orderDto.getApplicantEmail());  //OK
        order.setExecutor(orderDto.getExecutor());  // OK


        order.setModel(orderDto.getModel());  // NOK
//        System.out.println("orderDto.getModel() = " + orderDto.getModel());
//        System.out.println("order.getModel()) = " + order.getModel());

              order.setCategory(orderDto.getCategory());  // NOK
//        System.out.println();


        order.setSubject(orderDto.getSubject());   //OK
        order.setDescription(orderDto.getDescription());   //OK
        order.setDeadline(orderDto.getDeadline());  //OK
        order.setStatus(orderDto.getStatus());    // OK
        order.setSerialNumber(orderDto.getSerialNumber());  // OK
        order.setTotalPrice(orderDto.getTotalPrice());  // OK








        orderRepository.save(order);



    }

}
