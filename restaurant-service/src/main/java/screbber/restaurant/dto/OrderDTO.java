package screbber.restaurant.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderDTO {
    private String specialRequests;

    private List<OrderDishDTO> orderDishList;
}