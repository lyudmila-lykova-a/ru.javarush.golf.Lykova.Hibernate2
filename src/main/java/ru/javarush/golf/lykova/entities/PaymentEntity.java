package ru.javarush.golf.lykova.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "payment", schema = "movie")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public CustomerEntity customerEntity;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    public StaffEntity staffEntity;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    public RentalEntity rentalEntity;
}
