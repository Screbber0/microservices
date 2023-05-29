package screbber.restaurant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import screbber.restaurant.models.Dish;
import screbber.restaurant.services.DishService;

import java.util.List;

@RestController
@RequestMapping("/restaurant/dish")
public class DishController {

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/menu")
    public ResponseEntity<?> getAllDishes() {
        List<Dish> dishList = dishService.getAllDishes();
        if (dishList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dishList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Dish> getDishById(@PathVariable("id") Long id) {
        Dish dish = dishService.getDishById(id);
        return ResponseEntity.ok(dish);
    }


    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createDish(@RequestBody Dish dish) {
        try {
            Dish createdDish = dishService.createDish(dish);
            return ResponseEntity.ok(createdDish);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Incorrect dish");
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteDish(@PathVariable("id") Long id){
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }
}

