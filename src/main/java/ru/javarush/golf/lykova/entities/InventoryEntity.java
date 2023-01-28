package ru.javarush.golf.lykova.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "inventory", schema = "movie")
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer inventoryId;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @ManyToOne
    @JoinColumn(name = "film_id")
    public FilmEntity filmEntity;

    @ManyToOne
    @JoinColumn(name = "store_id")
    public StoreEntity storeEntity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id")
    private Set<RentalEntity> rentalEntities = new HashSet<>();
}
