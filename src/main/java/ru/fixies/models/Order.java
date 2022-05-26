package ru.fixies.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "customer_id", nullable = false)
    @ToString.Exclude
    private User customer;

    @Column(name = "applicant_name")
    private String applicantName;

    @Column(name = "applicant_phone")
    private String applicantPhone;

    @Column(name = "applicant_email")
    private String applicantEmail;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "executor_id", nullable = false)
    @ToString.Exclude
    private User executor;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "model_id", nullable = false)
    @ToString.Exclude
    private Model model;

    @Length(max = 255, message = "Subject length cannot exceed 255 characters!")
    @Column(name = "client_subject", nullable = false)
    private String clientSubject;

    @Column(name = "client_description", nullable = false)
    private String clientDescription;

    @Column(name = "product_preview_comment", nullable = false)
    private String productPreviewComment;

    @Column(name = "se_diagnostics_comment", nullable = false)
    private String seDiagnosticsComment;

    @Column(name = "se_provisional_repair_cost")
    private BigDecimal seProvisionalRepairCost;

    @Column(name = "se_repair_comment", nullable = false)
    private String seRepairComment;

    @Column(name = "deadline")
    private Date deadline;
//    private LocalDateTime deadline;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "status_id", nullable = false)
    @ToString.Exclude
    private Status status;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "warranty_id", nullable = false)
    @ToString.Exclude
    private Warranty warranty;


    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    private List<Comment> comments;

    @Length(max = 100, message = "The length of serial number cannot exceed 100 characters!")
    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "warranty_price")
    private BigDecimal warrantyPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return id != null && Objects.equals(id, order.id) &&
                Objects.equals(createdAt, order.createdAt) &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(executor, order.executor) &&
                Objects.equals(model, order.model) &&
                Objects.equals(clientSubject, order.clientSubject) &&
                Objects.equals(clientDescription, order.clientDescription) &&
                Objects.equals(productPreviewComment, order.productPreviewComment) &&
                Objects.equals(seDiagnosticsComment, order.seDiagnosticsComment) &&
                Objects.equals(seProvisionalRepairCost, order.seProvisionalRepairCost) &&
                Objects.equals(seRepairComment, order.seRepairComment) &&
                Objects.equals(deadline, order.deadline) &&
                Objects.equals(status, order.status) &&
                Objects.equals(comments, order.comments) &&
                Objects.equals(serialNumber, order.serialNumber) &&
                Objects.equals(totalPrice, order.totalPrice) &&
                Objects.equals(warrantyPrice, order.warrantyPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                createdAt,
                customer,
                executor,
                model,
                clientSubject,
                clientDescription,
                productPreviewComment,
                seDiagnosticsComment,
                seProvisionalRepairCost,
                seRepairComment,
                deadline,
                status,
                comments,
                serialNumber,
                totalPrice,
                warrantyPrice);
    }
}
