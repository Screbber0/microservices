package screbber.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import screbber.restaurant.models.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
