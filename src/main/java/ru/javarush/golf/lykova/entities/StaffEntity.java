package ru.javarush.golf.lykova.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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

    @Column(name = "address_id", nullable = false)
    private Integer addressId;

    @Column(name="picture",columnDefinition="blob")
    private byte[] picture;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "store_id", nullable = false)
    private Integer storeId;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "username", length = 16, nullable = false)
    private String username;

    @Column(name = "password", length = 40, nullable = false)
    private String password;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;
}
