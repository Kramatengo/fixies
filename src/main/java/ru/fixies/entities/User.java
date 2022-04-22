package ru.fixies.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Range(min = 3, max = 50, message = "The login must be between 3 and 50 in length!")
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Range(min = 8, max = 255, message = "The password must be between 8 and 255 in length!")
    @Column(name = "password", nullable = false)
    private String password;

    @Range(max = 150, message = "First name length must be less than 150 characters!")
    @Column(name = "first_name")
    private String firstName;

    @Range(max = 150, message = "Last name length must be less than 150 characters!")
    @Column(name = "last_name")
    private String lastName;

    @Range(max = 150, message = "Middle name length must be less than 150 characters!")
    @Column(name = "middle_names")
    private String middleNames;

    @Range(max = 100, message = "Email length must be less than 100 characters!")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Range(max = 100, message = "Email length must be less than 100 characters!")
    @Column(name = "phone")
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
    private Set<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleNames='" + middleNames + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id.equals(user.id) && login.equals(user.login) && password.equals(user.password) && Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) && Objects.equals(middleNames, user.middleNames) && email.equals(user.email) &&
                Objects.equals(phone, user.phone) && Objects.equals(createdAt, user.createdAt) && Objects.equals(updatedAt, user.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, firstName, lastName, middleNames, email, phone, createdAt, updatedAt);
    }
}
