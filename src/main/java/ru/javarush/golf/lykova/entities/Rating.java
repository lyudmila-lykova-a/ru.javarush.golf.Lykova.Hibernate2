package ru.javarush.golf.lykova.entities;

public enum Rating {
    G("G"), PG("PG"), PG13("PG-13"), R("R"), NC17("NC-17");

    private final String enumValue;

    Rating(String enumValue) {
        this.enumValue = enumValue;
    }

    public String getEnumValue() {
        return enumValue;
    }

    public static Rating of(String enumValue) {
        for (Rating rating : values()) {
            if (rating.enumValue.equals(enumValue)) {
                return rating;
            }
        }
        throw new UnsupportedOperationException("Unknown rating type " + enumValue);
    }
}
