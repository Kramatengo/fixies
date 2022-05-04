package ru.fixies.fixies.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fixies.fixies.model.Order;
import ru.fixies.fixies.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);


    @Query(
            value = "SELECT users.*, roles.* " +
                    "FROM users, roles " +
                    "WHERE " +
                    "ORDER BY id DESC ",
            // add countQuery = "SELECT count(*) FROM tbl_cs_models_new" to ORDER BY id
            countQuery = "SELECT count(*) FROM orders",
            nativeQuery = true)
    Page<Order> findAllOrders(Pageable pageable);

    @Query(
            value = "SELECT  users.* " +
                    "FROM users  ",

            nativeQuery = true)
    List<User> findAllUsersForOrderForm();


}
