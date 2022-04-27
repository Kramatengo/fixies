package ru.fixies.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StockDto {

    private Long id;
    private SpareDto spare;
    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
