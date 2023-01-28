package ru.javarush.golf.lykova.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "address", schema = "movie")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @Column(name = "address2", length = 50)
    private String address2;

    @Column(name = "district", length = 20, nullable = false)
    private String district;

    @Column(name = "postal_code", length = 10)
    private String postalCode;

    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @ManyToOne
    @JoinColumn(name = "city_id")
    public CityEntity cityEntity;

    @OneToOne(cascade = CascadeType.ALL, mappedBy="addressEntity")
    public StoreEntity storeEntity;

    @OneToOne(cascade = CascadeType.ALL, mappedBy="addressEntity")
    public CustomerEntity customerEntity;

    @OneToOne(cascade = CascadeType.ALL, mappedBy="addressEntity")
    public StaffEntity staffEntity;
}
