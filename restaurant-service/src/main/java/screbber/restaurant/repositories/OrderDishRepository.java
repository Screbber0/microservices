package screbber.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import screbber.restaurant.models.OrderDish;

public interface OrderDishRepository extends JpaRepository<OrderDish, Long> {
}
