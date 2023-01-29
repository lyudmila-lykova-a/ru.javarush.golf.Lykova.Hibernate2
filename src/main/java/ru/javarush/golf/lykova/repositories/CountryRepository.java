package ru.javarush.golf.lykova.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.golf.lykova.entities.*;

import java.time.LocalDateTime;
import java.util.Optional;

public class CountryRepository {

    private final SessionFactory sessionFactory;

    public CountryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<CountryEntity> findByName(String countryName) {
        try (Session session = sessionFactory.openSession()) {
            Query<CountryEntity> query = session.createQuery("from CountryEntity where country = ?1", CountryEntity.class);
            query.setParameter(1, countryName);
            return query.uniqueResultOptional();
        }
    }
}
