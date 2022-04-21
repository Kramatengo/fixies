package ru.fixies.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fixies.model.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {


    @Query(
            value = "SELECT orders.* " +
                    "FROM orders " +
                    "ORDER BY id DESC ",
            // add countQuery = "SELECT count(*) FROM tbl_cs_models_new" to ORDER BY id
            countQuery = "SELECT count(*) FROM orders",
            nativeQuery = true)
    Page<Orders> findAllOrders(Pageable pageable);


}
