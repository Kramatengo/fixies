package ru.fixies.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fixies.models.Spare;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StockDto {

    private Long id;
    private Spare spare;
    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
