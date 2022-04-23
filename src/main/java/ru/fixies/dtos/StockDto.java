package ru.fixies.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fixies.models.Spare;
import ru.fixies.models.Stock;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StockDto {

    private Long id;
    private Spare spare;
    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public StockDto(Stock stock) {
        this.id = stock.getId();
        this.spare = stock.getSpare();
        this.quantity = stock.getQuantity();
    }
}
