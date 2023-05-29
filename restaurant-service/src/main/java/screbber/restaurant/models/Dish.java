package screbber.restaurant.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "is_available")
    private boolean isAvailable;

    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    private List<OrderDish> orderDishes;
}