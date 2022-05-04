package ru.fixies.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Users")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 3, max = 50, message = "The login must be between 3 and 50 in length!")
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Length(min = 8, max = 255, message = "The password must be between 8 and 255 in length!")
    @Column(name = "password", nullable = false)
    private String password;

    @Length(max = 150, message = "First name length must be less than 150 characters!")
    @Column(name = "first_name")
    private String firstName;

    @Length(max = 150, message = "Last name length must be less than 150 characters!")
    @Column(name = "last_name")
    private String lastName;

    @Length(max = 150, message = "Middle name length must be less than 150 characters!")
    @Column(name = "middle_names")
    private String middleNames;

    @Length(max = 100, message = "Email length must be less than 100 characters!")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Length(max = 100, message = "Email length must be less than 100 characters!")
    @Column(name = "phone", unique = true)
    private String phone;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles;

    // TODO: рассмотреть необходимость листов ордеров
    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private List<Order> ordersCustomers;

    @OneToMany(mappedBy = "executor")
    @ToString.Exclude
    private List<Order> ordersExecutors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id != null && Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) &&
                Objects.equals(middleNames, user.middleNames) && Objects.equals(email, user.email) && Objects.equals(phone, user.phone) &&
                Objects.equals(createdAt, user.createdAt) && Objects.equals(updatedAt, user.updatedAt) &&
                Objects.equals(roles, user.roles) && Objects.equals(ordersCustomers, user.ordersCustomers) &&
                Objects.equals(ordersExecutors, user.ordersExecutors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, firstName, lastName, middleNames, email, phone, createdAt, updatedAt,
                roles, ordersCustomers, ordersExecutors);
    }
}
