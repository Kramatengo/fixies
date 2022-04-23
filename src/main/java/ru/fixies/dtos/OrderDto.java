package ru.fixies.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fixies.models.Model;
import ru.fixies.models.Order;
import ru.fixies.models.Status;
import ru.fixies.models.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private LocalDateTime createdAt;
    private User customer;
    private User executor;
    private Model model;
    private String subject;
    private String description;
    private LocalDateTime deadline;
    private Status status;
    private String serialNumber;
    private BigDecimal totalPrice;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.createdAt = order.getCreatedAt();
        this.customer = order.getCustomer();
        this.executor = order.getExecutor();
        this.model = order.getModel();
        this.subject = order.getSubject();
        this.description = order.getDescription();
        this.deadline = order.getDeadline();
        this.status = order.getStatus();
        this.serialNumber = order.getSerialNumber();
        this.totalPrice = order.getTotalPrice();

    }
}
