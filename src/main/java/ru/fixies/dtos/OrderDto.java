package ru.fixies.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private LocalDateTime createdAt;
    private UserDto customer;
    private String applicantName;
    private String applicantPhone;
    private String applicantEmail;
    private UserDto executor;
    private ModelDto model;
    private String clientSubject;
    private String clientDescription;
    private String productPreviewComment;
    private String seDiagnosticsComment;
    private BigDecimal seProvisionalRepairCost;
    private String seRepairComment;
    private Date deadline;
    //    private LocalDateTime deadline;
    private StatusDto status;
    private WarrantyDto warranty;
    private String serialNumber;
    private BigDecimal totalPrice;
    private BigDecimal warrantyPrice;


}
