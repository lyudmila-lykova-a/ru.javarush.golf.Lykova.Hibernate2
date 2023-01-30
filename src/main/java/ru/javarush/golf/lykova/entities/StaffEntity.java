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
@Table(name = "staff", schema = "movie")
public class StaffEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Integer staffId;

    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;

    @Column(name="picture",columnDefinition="blob")
    private byte[] picture;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "username", length = 16, nullable = false)
    private String username;

    @Column(name = "password", length = 40, nullable = false)
    private String password;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @OneToOne
    @JoinColumn(name = "address_id")
    public AddressEntity addressEntity;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_id")
    private Set<PaymentEntity> paymentEntities = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_id")
    private Set<RentalEntity> rentalEntities = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    public StoreEntity storeEntity;

    @OneToOne(cascade = CascadeType.ALL, mappedBy="staffEntity")
    public StoreEntity manageStaffStoreEntity;
}
