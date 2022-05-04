package ru.fixies.fixies.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @Column(name = "applicant_name")
    private String applicantName;

    @Column(name = "applicant_phone")
    private String applicantPhone;

    @Column(name = "applicant_email")
    private String applicantEmail;

    @ManyToOne
    @JoinColumn(name = "executor_id")
    private User executor;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
//    private Long modelId;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
//
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "subject")
    private String subject;

    @Column(name = "description")
    private String description;

    @Column(name = "deadline")
    private Date deadline;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
//    private Long statusId;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "total_price")
    private Float totalPrice;

}
