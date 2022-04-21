package ru.fixies.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fixies.model.Models;
import ru.fixies.model.Orders;
import ru.fixies.model.Statuses;
import ru.fixies.model.Users;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class OrdersDto {

    private Long id;
    private LocalDateTime createdAt;
    private Users customer;
    private Users executor;
    private Models model;
    private String subject;
    private String description;
    private Date deadline;
    private Statuses statuses;
    private String serialNumber;
    private Float totalPrice;

    public OrdersDto(Orders orders) {
        this.id = orders.getId();
        this.createdAt = orders.getCreatedAt();
        this.customer = orders.getCustomer();
        this.executor = orders.getExecutor();
        this.model = orders.getModel();
        this.subject = orders.getSubject();
        this.description = orders.getDescription();
        this.deadline = orders.getDeadline();
        this.statuses = orders.getStatuses();
        this.serialNumber = orders.getSerialNumber();
        this.totalPrice = orders.getTotalPrice();

    }
}
