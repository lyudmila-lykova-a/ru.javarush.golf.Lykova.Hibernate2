package ru.javarush.golf.lykova.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "store", schema = "movie")
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Integer storeId;

    @Column(name = "manager_staff_id", nullable = false)
    private Integer managerStaffId;

    @Column(name = "address_id", nullable = false)
    private Integer addressId;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;
}
