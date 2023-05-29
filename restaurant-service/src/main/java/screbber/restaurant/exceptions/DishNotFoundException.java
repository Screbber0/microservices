package screbber.restaurant.exceptions;

public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException(Long id) {
        super("Dish not found with id: " + id);
    }
}
