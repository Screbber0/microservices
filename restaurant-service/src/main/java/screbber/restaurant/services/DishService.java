package screbber.restaurant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import screbber.restaurant.exceptions.DishNotFoundException;
import screbber.restaurant.models.Dish;
import screbber.restaurant.repositories.DishRepository;

import java.util.Optional;

@Service
public class DishService {

    private final DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }


    public Dish createDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public Page<Dish> getAllDishes(Pageable pageable) {
        return dishRepository.findAll(pageable);
    }


    public Dish getDishById(Long id) {
        Optional<Dish> dish = dishRepository.findById(id);
        return dish.orElseThrow(() -> new DishNotFoundException(id));
    }

    public Dish updateDish(Long id, Dish updatedDish) {
        return dishRepository.findById(id)
                .map(dish -> {
                    dish.setName(updatedDish.getName());
                    dish.setPrice(updatedDish.getPrice());
                    dish.setQuantity(updatedDish.getQuantity());
                    dish.setAvailable(updatedDish.isAvailable());
                    dish.setDescription(updatedDish.getDescription());
                    return dishRepository.save(dish);
                })
                .orElseThrow(() -> new DishNotFoundException(id));
    }

    public void deleteDish(Long id) {
        if (dishRepository.existsById(id)) {
            dishRepository.deleteById(id);
        } else {
            throw new DishNotFoundException(id);
        }
    }
}
