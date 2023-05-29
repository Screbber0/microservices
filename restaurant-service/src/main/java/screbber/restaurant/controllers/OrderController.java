package screbber.restaurant.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import screbber.restaurant.dto.OrderDTO;
import screbber.restaurant.models.order.OrderResponse;
import screbber.restaurant.services.OrderService;

@RestController
@RequestMapping("/restaurant/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(
            HttpServletRequest request,
            @RequestBody @Valid OrderDTO orderDTO) {

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            return orderService.consumeUserResponse(orderDTO, token);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable("id") Long orderId) {
        return orderService.getOrderById(orderId);
    }


}
