package ru.fixies.fixies.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fixies.fixies.model.*;


import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private LocalDateTime createdAt;

    private User customer;
    //    private Long customer;
    private String applicantName;
    private String applicantPhone;
    private String applicantEmail;
    private User executor;
    private Model model;
//    private String model;

    private Category category;
    private Brand brand;
    private String subject;
    private String description;
    private Date deadline;
    private Status status;
    private String serialNumber;
    private Float totalPrice;

    public OrderDto(Order orders) {
        this.id = orders.getId();
        this.createdAt = orders.getCreatedAt();

//        this.customer = orders.getCustomer();
        this.customer.setId(orders.getCustomer().getId());

        this.applicantName = orders.getApplicantName();
        this.applicantPhone = orders.getApplicantPhone();
        this.applicantEmail = orders.getApplicantEmail();

//        this.executor = orders.getExecutor();
//        this.executor.setId(orders.getExecutor().getId());
//        this.executor.setId(45L);
        this.executor.setId(orders.getCustomer().getId());


//        this.model = orders.getModel();
        this.model.setId(model.getId());
        this.model.setCategory(model.getCategory());
        this.model.setBrand(model.getBrand());
//        this.model = orders.getModel();
//        this.model.setName(orders.getModel().getName());

//        this.category = orders.getCategory();
        this.category.setId(orders.getCategory().getId());


        this.subject = orders.getSubject();
        this.description = orders.getDescription();
        this.deadline = orders.getDeadline();
        this.status = orders.getStatus();
        this.serialNumber = orders.getSerialNumber();
        this.totalPrice = orders.getTotalPrice();

    }
}
