package ru.javarush.golf.lykova.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "rental", schema = "movie")
public class RentalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Integer rentalId;

    @Column(name = "rental_date", nullable = false)
    private LocalDateTime rentalDate;

    @Column(name = "inventory_id", nullable = false)
    private Integer inventoryId;

    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "staff_id", nullable = false)
    private Integer staffId;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
