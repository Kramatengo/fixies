package ru.fixies.models;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserRoles {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserRoles userRoles = (UserRoles) o;
        return userId != null && Objects.equals(userId, userRoles.userId) && Objects.equals(roleId, userRoles.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }
}
