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
@Table(name = "actor", schema = "movie")
public class ActorEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Integer actorId;

    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="film_actor",
            joinColumns=  @JoinColumn(name="actor_id", referencedColumnName="actor_id"),
            inverseJoinColumns= @JoinColumn(name="film_id", referencedColumnName="film_id") )
    private Set<FilmEntity> filmEntities = new HashSet<>();

}
