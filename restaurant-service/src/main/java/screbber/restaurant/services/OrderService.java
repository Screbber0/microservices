package screbber.restaurant.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import screbber.restaurant.dto.OrderDTO;
import screbber.restaurant.dto.OrderDishDTO;
import screbber.restaurant.exceptions.DishNotFoundException;
import screbber.restaurant.exceptions.OrderNotFoundException;
import screbber.restaurant.models.Dish;
import screbber.restaurant.models.OrderDish;
import screbber.restaurant.models.Person;
import screbber.restaurant.models.order.Order;
import screbber.restaurant.models.order.OrderResponse;
import screbber.restaurant.models.order.OrderStatus;
import screbber.restaurant.repositories.DishRepository;
import screbber.restaurant.repositories.OrderDishRepository;
import screbber.restaurant.repositories.OrderRepository;
import screbber.restaurant.repositories.PeopleRepository;
import screbber.restaurant.security.JWTUtil;

import java.util.Optional;

@Service
public class OrderService {

    private final PeopleRepository peopleRepository;
    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;
    private final OrderDishRepository orderDishRepository;

    private final ModelMapper modelMapper;

    private final JWTUtil jwtUtil;

    @Autowired
    public OrderService(PeopleRepository peopleRepository, OrderRepository orderRepository, DishRepository dishRepository, OrderDishRepository orderDishRepository, ModelMapper modelMapper, JWTUtil jwtUtil) {
        this.peopleRepository = peopleRepository;
        this.orderRepository = orderRepository;
        this.dishRepository = dishRepository;
        this.orderDishRepository = orderDishRepository;
        this.modelMapper = modelMapper;
        this.jwtUtil = jwtUtil;
    }

    private OrderDish convertToOrderDish(OrderDishDTO orderDishDTO) {
        return this.modelMapper.map(orderDishDTO, OrderDish.class);
    }

    public ResponseEntity<OrderResponse> consumeUserResponse(OrderDTO orderDTO, String token) {
        String username = jwtUtil.validateTokenAndRetrieveClaim(token);
        Person person = peopleRepository.findByUsername(username).orElseThrow();

        Order order = new Order();

        order.setSpecialRequests(orderDTO.getSpecialRequests());
        order.setStatus(OrderStatus.RECEIVED);
        order.setUser(person);
        orderRepository.save(order);

        for (OrderDishDTO orderDishDTO : orderDTO.getOrderDishList()) {
            Dish dish = dishRepository.findById(orderDishDTO.getDishId()).orElseThrow();

            OrderDish orderDish = convertToOrderDish(orderDishDTO);
            orderDish.setOrder(order);
            orderDish.setDish(dish);
            orderDishRepository.save(orderDish);
        }
        return ResponseEntity.ok(new OrderResponse(order));
    }


    public ResponseEntity<OrderResponse> getOrderById(Long order_id) {
        Optional<Order> order = orderRepository.findById(order_id);

        return order.map(value -> ResponseEntity.ok(new OrderResponse(value)))
                .orElseThrow(() -> new OrderNotFoundException(order_id));
    }
}
