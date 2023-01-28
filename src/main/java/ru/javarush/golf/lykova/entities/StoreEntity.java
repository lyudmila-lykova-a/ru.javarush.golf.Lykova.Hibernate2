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
@Table(name = "store", schema = "movie")
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Integer storeId;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @OneToOne
    @JoinColumn(name = "address_id")
    public AddressEntity addressEntity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id")
    private Set<CustomerEntity> customerEntities = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id")
    private Set<InventoryEntity> inventoryEntities = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id")
    private Set<StaffEntity> staffEntities = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "manager_staff_id")
    public StaffEntity staffEntity;

}
