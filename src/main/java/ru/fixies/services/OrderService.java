package ru.fixies.services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;
import ru.fixies.dtos.ModelDto;
import ru.fixies.dtos.OrderDto;
import ru.fixies.dtos.UserDto;
import ru.fixies.exceptions.DataValidationException;
import ru.fixies.exceptions.ResourceNotFoundException;
import ru.fixies.mapper.ModelMapper;
import ru.fixies.models.*;
import ru.fixies.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
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
    private final WarrantyService warrantyService;

    @Transactional
    public void createOrder(Principal principal, OrderDto orderDto) {
        if (!validateEmail(orderDto.getApplicantEmail())) {
            ArrayList<String> messages = new ArrayList<>();
            messages.add("Введен не корректный email " + orderDto.getApplicantEmail());
            throw new DataValidationException(messages);
        }

        User user = userService.findByLogin(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти пользователя при оформлении заказа. Имя пользователя: " + principal.getName()));
        Order order = new Order();
        order.setCustomer(user);
        order.setApplicantName(orderDto.getApplicantName());  //OK
        order.setApplicantPhone(orderDto.getApplicantPhone());  //OK
        order.setApplicantEmail(orderDto.getApplicantEmail()); //OK
        order.setModel(modelService.findByName(orderDto.getModel().getName()));
        order.setSerialNumber(orderDto.getSerialNumber());
        order.setClientSubject(orderDto.getClientSubject());
        order.setClientDescription(orderDto.getClientDescription());
        order.setProductPreviewComment(orderDto.getProductPreviewComment());
        order.setWarranty(warrantyService.findByName(orderDto.getWarranty().getName()));
        order.setExecutor(userService.findByLogin(orderDto.getExecutor().getLogin()).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти пользователя при оформлении заказа. Имя пользователя: " + orderDto.getExecutor().getLogin())));
        order.setSeDiagnosticsComment(orderDto.getSeDiagnosticsComment());
        order.setSeProvisionalRepairCost(orderDto.getSeProvisionalRepairCost());
        order.setDeadline(orderDto.getDeadline());
        order.setSeRepairComment(orderDto.getSeRepairComment());
        order.setStatus(statusService.findByName(orderDto.getStatus().getName()));
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setWarrantyPrice(orderDto.getWarrantyPrice());
        orderRepository.save(order);
    }

    @Transactional
    public void updateOrderFromDto(OrderDto orderDto) {
        Order order = orderRepository.findById(orderDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Order id = " + orderDto.getId() + " not found"));

        order.setApplicantName(orderDto.getApplicantName());  //OK
        order.setApplicantPhone(orderDto.getApplicantPhone());  //OK
        order.setApplicantEmail(orderDto.getApplicantEmail());  //OK


        order.setDeadline(orderDto.getDeadline());
        order.setClientSubject(orderDto.getClientSubject());
        order.setSerialNumber(orderDto.getSerialNumber());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setWarrantyPrice(orderDto.getWarrantyPrice());
        order.setClientDescription(orderDto.getClientDescription());
        order.setProductPreviewComment(orderDto.getProductPreviewComment());
        order.setSeDiagnosticsComment(orderDto.getSeDiagnosticsComment());
        order.setSeProvisionalRepairCost(orderDto.getSeProvisionalRepairCost());
        order.setSeRepairComment(orderDto.getSeRepairComment());
        Status status = statusService.findByName(orderDto.getStatus().getName());
        order.setStatus(status);
        Warranty warranty = warrantyService.findByName(orderDto.getWarranty().getName());
        order.setWarranty(warranty);
        Model model = modelService.findByName(orderDto.getModel().getName());
        order.setModel(model);
        order.setExecutor(userService.findByLogin(orderDto.getExecutor().getLogin()).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти пользователя при оформлении заказа. Имя пользователя: " + orderDto.getExecutor().getLogin())));
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

    private boolean validateEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
