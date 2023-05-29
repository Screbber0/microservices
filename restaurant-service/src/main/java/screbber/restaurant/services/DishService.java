package screbber.restaurant.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import screbber.restaurant.models.Dish;
import screbber.restaurant.repositories.DishRepository;

import java.util.List;
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

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Dish getDishById(Long id) {
        Optional<Dish> dish = dishRepository.findById(id);
        return dish.orElseThrow(() -> new EntityNotFoundException("Dish not found with id: " + id));
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
                .orElseThrow(() -> new EntityNotFoundException("Dish not found with id: " + id));
    }

    public void deleteDish(Long id) {
        if (dishRepository.existsById(id)) {
            dishRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Dish not found with id: " + id);
        }
    }
}
