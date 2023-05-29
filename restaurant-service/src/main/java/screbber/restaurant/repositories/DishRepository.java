package screbber.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import screbber.restaurant.models.Dish;

import javax.swing.text.html.Option;

public interface DishRepository extends JpaRepository<Dish, Long> {
}