package ru.fixies.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpareDto implements Serializable {

    private Long id;
    @Length(max = 255, message = "The length of the spare part name cannot exceed 255 characters!")
    private String name;
    private BigDecimal price;
    private List<StockDto> stocks;

}
