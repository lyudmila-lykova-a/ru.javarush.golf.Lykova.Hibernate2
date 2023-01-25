package ru.javarush.golf.lykova.entities.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.javarush.golf.lykova.entities.Rating;

@Converter
public class RatingConverter implements AttributeConverter<Rating, String> {

    @Override
    public String convertToDatabaseColumn(Rating rating) {
        if (rating == null) {
            return null;
        }
        return rating.getEnumValue();
    }

    @Override
    public Rating convertToEntityAttribute(String rating) {
        if (rating == null || rating.isEmpty()) {
            return null;
        }
        return Rating.of(rating);
    }
}

