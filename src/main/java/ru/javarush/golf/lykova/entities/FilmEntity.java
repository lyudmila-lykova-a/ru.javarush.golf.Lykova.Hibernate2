package ru.javarush.golf.lykova.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.javarush.golf.lykova.entities.converters.RatingConverter;
import ru.javarush.golf.lykova.entities.converters.SetConverter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "film", schema = "movie")
public class FilmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Integer filmId;

    @Column(name = "title", length = 128, nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "rental_duration", nullable = false)
    private Integer rentalDuration;

    @Column(name = "rental_rate", nullable = false)
    private Double rentalRate;

    @Column(name = "length")
    private Integer length;

    @Column(name = "replacement_cost", nullable = false)
    private Double replacementCost;

    @Column(name = "rating")
    @Convert(converter = RatingConverter.class)
    private Rating rating;

    @Column(name = "special_features")
    @Convert(converter = SetConverter.class)
    private Set<String> specialFeatures;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="film_actor",
            joinColumns=  @JoinColumn(name="film_id", referencedColumnName="film_id"),
            inverseJoinColumns= @JoinColumn(name="actor_id", referencedColumnName="actor_id") )
    private Set<ActorEntity> actorEntities = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="film_category",
            joinColumns=  @JoinColumn(name="film_id", referencedColumnName="film_id"),
            inverseJoinColumns= @JoinColumn(name="category_id", referencedColumnName="category_id") )
    private Set<CategoryEntity> categoryEntities = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_language_id")
    public LanguageEntity originalLanguageEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    public LanguageEntity languageEntity;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id")
    private Set<InventoryEntity> inventoryEntities = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy="filmEntity")
    public FilmTextEntity filmTextEntity;
}
