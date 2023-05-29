package screbber.restaurant.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDishDTO {
    private Long dishId;

    @Min(value = 0)
    private int quantity;

    @Min(value = 0, message = "Цена не может быть меньше 0")
    private Long price;

}
