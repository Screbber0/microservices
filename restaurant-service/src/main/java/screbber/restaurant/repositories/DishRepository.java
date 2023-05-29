package screbber.restaurant.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import screbber.restaurant.models.Dish;

public interface DishRepository extends PagingAndSortingRepository<Dish, Long> {
    Page<Dish> findAll(Pageable pageable);
}
