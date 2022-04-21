package ru.fixies.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.fixies.model.Spares;
import ru.fixies.model.Stock;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StockDto {

    private Long id;
    private Spares spares;
    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public StockDto(Stock stock) {
        this.id = stock.getId();
        this.spares = stock.getSpares();
        this.quantity = stock.getQuantity();
    }
}
