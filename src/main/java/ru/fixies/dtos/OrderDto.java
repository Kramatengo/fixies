package ru.fixies.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private LocalDateTime createdAt;
    private UserDto customer;
    private UserDto executor;
    private ModelDto model;
    private String subject;
    private String description;
    private LocalDateTime deadline;
    private StatusDto status;
    private String serialNumber;
    private BigDecimal totalPrice;

}
