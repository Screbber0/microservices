package screbber.restaurant.models.order;

import jakarta.persistence.*;
import lombok.Data;
import screbber.restaurant.models.OrderDish;
import screbber.restaurant.models.Person;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @Column(name = "special_requests")
    private String specialRequests;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Person user;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDish> orderDishes;

    public Order() {
    }
}