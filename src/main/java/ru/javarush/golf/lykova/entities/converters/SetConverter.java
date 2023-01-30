package ru.javarush.golf.lykova.entities.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.javarush.golf.lykova.entities.Rating;

import java.util.Set;

@Converter
public class SetConverter implements AttributeConverter<Set<String>, String> {

    @Override
    public String convertToDatabaseColumn(Set<String> set) {
        if (set == null) {
            return null;
        }
        return String.join(",", set);
    }

    @Override
    public Set<String> convertToEntityAttribute(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return Set.of(value.split(","));
    }
}

