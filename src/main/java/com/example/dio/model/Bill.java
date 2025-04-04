package com.example.dio.model;

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
@EntityListeners(AuditingEntityListener.class)
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long billId;

    @Column(name = "generated_at")
    @CreatedDate
    private LocalDateTime generatedAt;

    @Column(name = "total_payable_amount")
    private double totalPayableAmount;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Order> orders;
}
