package ru.javarush.golf.lykova.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "inventory", schema = "movie")
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer inventoryId;

    @Column(name = "film_id", nullable = false)
    private Integer filmId;

    @Column(name = "store_id", nullable = false)
    private Integer storeId;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

}
