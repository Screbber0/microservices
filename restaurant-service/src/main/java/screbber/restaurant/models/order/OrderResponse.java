package screbber.restaurant.models.order;

import lombok.Data;

@Data
public class OrderResponse {
    private Long id;
    private OrderStatus status;
    private String specialRequests;
    private String userEmail;

    public OrderResponse(Order order){
        id = order.getId();
        status = order.getStatus();
        specialRequests = order.getSpecialRequests();
        userEmail = order.getUser().getEmail();
    }
}
