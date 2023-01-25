package ru.javarush.golf.lykova.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.javarush.golf.lykova.entities.converters.RatingConverter;
import ru.javarush.golf.lykova.entities.converters.SetConverter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString
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

    @Column(name = "language_id", nullable = false)
    private Integer languageId;

    @Column(name = "original_language_id", nullable = false)
    private Integer originalLanguageId;

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
}
