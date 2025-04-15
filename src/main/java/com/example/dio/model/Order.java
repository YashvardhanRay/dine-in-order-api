package com.example.dio.model;

import com.example.dio.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class Order{

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "ordered_at")
    @CreatedDate
    private LocalDateTime orderedAt;

    @Column(name = "total_amount")
    private double totalAmount;

    @ManyToOne
    private RestaurantTable restaurantTable;

    @OneToMany(fetch = FetchType.EAGER)
    private List<CartItem> cartItems;

}
