package screbber.restaurant.repositories;


import org.springframework.data.repository.PagingAndSortingRepository;
import screbber.restaurant.models.Dish;

import java.util.Optional;

public interface DishRepository extends PagingAndSortingRepository<Dish, Long> {
    Optional<Dish> findById(Long id);

    Dish save(Dish dish);

    boolean existsById(Long id);

    void deleteById(Long id);
}
