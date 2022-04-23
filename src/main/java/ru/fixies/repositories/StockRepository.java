package ru.fixies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fixies.models.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {


}
