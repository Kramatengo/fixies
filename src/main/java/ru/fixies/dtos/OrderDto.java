package ru.fixies.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fixies.models.Model;
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

}
